package com.synthbyte.scanmate.utils

import java.util.Locale
import kotlin.math.abs
import kotlin.math.min

/**
 * Conservative OCR post-processing for ScanMate AI Pro.
 *
 * This class intentionally avoids global character swaps such as i->l or 0->O.
 * It only fixes spacing/punctuation patterns that are very likely to be OCR noise,
 * then applies a small dictionary through bounded edit-distance checks.
 */
object OcrPostProcessor {
    data class Options(
        val conservativeCorrection: Boolean = true,
        val useBuiltInDictionary: Boolean = true,
        val useCustomDictionary: Boolean = true,
        val emailMarkdownLinks: Boolean = true
    )

    private val builtInDictionary = linkedSetOf(
        "Preface",
        "Authors",
        "Class",
        "Computer",
        "Chapter",
        "Curriculum",
        "Subjective",
        "Objective",
        "Students",
        "Information",
        "Website",
        "Email",
        "Approved"
    )

    private val tldAlternation = listOf(
        "com", "org", "net", "edu", "gov", "pk", "in", "uk", "us", "io", "ai", "co",
        "info", "biz", "me", "app", "dev", "online", "site", "xyz"
    ).joinToString("|")

    private val emailSpacingRegex = Regex(
        """(?ix)\b([a-z0-9._%+\-]+)\s*@\s*([a-z0-9][a-z0-9\-\s.]*(?:\s*\.\s*(?:$tldAlternation)){1,3})\b"""
    )

    private val domainSpacingRegex = Regex(
        """(?ix)\b((?:https?\s*:\s*/\s*/)?(?:www\s*\.\s*)?[a-z0-9][a-z0-9\-]{1,}(?:\s*\.\s*[a-z0-9][a-z0-9\-]{1,})*(?:\s*\.\s*(?:$tldAlternation)){1,3})\b"""
    )

    private val wordRegex = Regex("""\b[A-Za-z][A-Za-z'’-]*\b""")
    private val protectedTokenRegex = Regex(
        """(?ix)(?:\b(?:https?://|www\.)\S+\b)|(?:\b\S+@\S+\b)|(?:\b\d{1,4}[-/.]\d{1,2}[-/.]\d{1,4}\b)|(?:\b(?:rs|pkr|usd|eur|gbp)?\s*\d+[\d,]*(?:\.\d+)?\b)|(?:\b[A-Z]{2,}\d+[A-Z0-9-]*\b)|(?:\b[A-Za-z0-9_\-]+\.(?:pdf|docx?|txt|jpg|jpeg|png|webp|xlsx?)\b)"""
    )

    fun normalize(
        raw: String,
        customWords: Set<String> = emptySet(),
        options: Options = Options()
    ): String {
        if (raw.isBlank()) return ""

        var text = raw
            .replace("\r\n", "\n")
            .replace('\r', '\n')
            .replace(Regex("[\\t ]+"), " ")
            .replace(Regex(" *\n *"), "\n")
            .replace(Regex("\n{3,}"), "\n\n")

        text = normalizeEmailSpacing(text, options.emailMarkdownLinks)
        text = normalizeUrlAndDomainSpacing(text)

        if (options.conservativeCorrection) {
            val dictionary = buildDictionary(customWords, options)
            if (dictionary.isNotEmpty()) {
                text = correctDictionaryWords(text, dictionary)
            }
        }

        return text
            .replace(Regex("[ \\t]{2,}"), " ")
            .lines()
            .joinToString("\n") { it.trimEnd() }
            .joinToStringPreservingParagraphs()
            .trim()
    }

    private fun normalizeEmailSpacing(value: String, markdownLinks: Boolean): String {
        return emailSpacingRegex.replace(value) { match ->
            val local = match.groupValues[1].replace(Regex("\\s+"), "")
            val domain = normalizeDomain(match.groupValues[2])
            val email = "$local@$domain"
            if (markdownLinks && !match.value.contains("mailto:", ignoreCase = true)) {
                "[$email](mailto:$email)"
            } else {
                email
            }
        }
    }

    private fun normalizeUrlAndDomainSpacing(value: String): String {
        return domainSpacingRegex.replace(value) { match ->
            normalizeDomain(match.value)
                .replace(Regex("(?i)^https?\\s*:\\s*/\\s*/")) { scheme ->
                    scheme.value.lowercase(Locale.US).replace(Regex("\\s+"), "")
                }
                .replace(Regex("(?i)^www\\s*\\."), "www.")
        }
    }

    private fun normalizeDomain(value: String): String = value
        .replace(Regex("\\s*\\.\\s*"), ".")
        .replace(Regex("\\s+"), "")
        .lowercase(Locale.US)
        .trim('.', ',', ';', ':')

    private fun buildDictionary(customWords: Set<String>, options: Options): Set<String> {
        val result = linkedSetOf<String>()
        if (options.useBuiltInDictionary) result += builtInDictionary
        if (options.useCustomDictionary) {
            customWords.mapNotNullTo(result) { sanitizeDictionaryWord(it) }
        }
        return result
    }

    private fun sanitizeDictionaryWord(word: String): String? {
        val clean = word.trim().trim('.', ',', ':', ';', '!', '?')
        if (clean.length < 5) return null
        if (!clean.any { it.isLetter() }) return null
        if (clean.any { it.isDigit() || it == '@' || it == '/' || it == '_' }) return null
        return clean
    }

    private fun correctDictionaryWords(text: String, dictionary: Set<String>): String {
        val protectedRanges = protectedTokenRegex.findAll(text).map { it.range }.toList()
        return wordRegex.replace(text) { match ->
            val token = match.value
            if (protectedRanges.any { match.range.first <= it.last && match.range.last >= it.first }) return@replace token
            correctToken(token, dictionary) ?: token
        }
    }

    private fun correctToken(token: String, dictionary: Set<String>): String? {
        if (token.length < 5) return null
        if (token.all { it.isUpperCase() }) return null
        if (token.any { it.isDigit() || it == '@' || it == '/' || it == '_' || it == '.' }) return null

        val lower = token.lowercase(Locale.US)
        val candidate = dictionary
            .asSequence()
            .filter { abs(it.length - token.length) <= 2 }
            .mapNotNull { word ->
                val wordLower = word.lowercase(Locale.US)
                val maxDistance = if (word.length <= 8) 1 else 2
                val distance = boundedEditDistance(lower, wordLower, maxDistance)
                val foldedDistance = boundedEditDistance(confusionFold(lower), confusionFold(wordLower), maxDistance)
                val score = min(distance, foldedDistance)
                if (score <= maxDistance && strongEnoughMatch(token, word, score)) word to score else null
            }
            .sortedWith(compareBy<Pair<String, Int>> { it.second }.thenBy { it.first.length })
            .firstOrNull()
            ?.first
            ?: return null

        if (candidate.equals(token, ignoreCase = false)) return null
        return preserveCapitalization(token, candidate)
    }

    private fun strongEnoughMatch(token: String, candidate: String, distance: Int): Boolean {
        if (distance <= 0) return true
        if (token.length <= 7 && distance > 1) return false
        val tokenFolded = confusionFold(token.lowercase(Locale.US))
        val candidateFolded = confusionFold(candidate.lowercase(Locale.US))
        val sameStart = tokenFolded.firstOrNull() == candidateFolded.firstOrNull()
        val sameEnd = tokenFolded.lastOrNull() == candidateFolded.lastOrNull()
        return sameStart || sameEnd || token.length >= 10
    }

    private fun confusionFold(value: String): String = value
        .replace('0', 'o')
        .replace('1', 'l')
        .replace('i', 'l')
        .replace("rn", "m")
        .replace("cl", "d")

    private fun preserveCapitalization(original: String, replacement: String): String {
        return when {
            original.all { it.isUpperCase() } -> replacement.uppercase(Locale.US)
            original.firstOrNull()?.isUpperCase() == true -> replacement.replaceFirstChar { it.titlecase(Locale.US) }
            else -> replacement.lowercase(Locale.US)
        }
    }

    private fun boundedEditDistance(a: String, b: String, maxDistance: Int): Int {
        if (abs(a.length - b.length) > maxDistance) return maxDistance + 1
        if (a == b) return 0
        var previous = IntArray(b.length + 1) { it }
        var current = IntArray(b.length + 1)
        for (i in 1..a.length) {
            current[0] = i
            var rowMin = current[0]
            for (j in 1..b.length) {
                val cost = if (a[i - 1] == b[j - 1]) 0 else 1
                current[j] = minOf(
                    previous[j] + 1,
                    current[j - 1] + 1,
                    previous[j - 1] + cost
                )
                rowMin = min(rowMin, current[j])
            }
            if (rowMin > maxDistance) return maxDistance + 1
            val swap = previous
            previous = current
            current = swap
        }
        return previous[b.length]
    }

    private fun String.joinToStringPreservingParagraphs(): String {
        return lines()
            .joinToString("\n") { line -> if (line.isBlank()) "" else line.trimEnd() }
            .replace(Regex("\n{3,}"), "\n\n")
    }
}
