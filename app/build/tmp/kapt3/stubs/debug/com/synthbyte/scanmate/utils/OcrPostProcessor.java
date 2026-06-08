package com.synthbyte.scanmate.utils;

/**
 * Conservative OCR post-processing for ScanMate AI Pro.
 *
 * This class intentionally avoids global character swaps such as i->l or 0->O.
 * It only fixes spacing/punctuation patterns that are very likely to be OCR noise,
 * then applies a small dictionary through bounded edit-distance checks.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\f\b\u00c7\u0002\u0018\u00002\u00020\u0001:\u0001-B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\rH\u0002J$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u001e\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012H\u0002J \u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001c\u001a\u00020\u00052\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012H\u0002J(\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00052\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u0018\u0010 \u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u0018\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u0005H\u0002J\u0012\u0010\'\u001a\u0004\u0018\u00010\u00052\u0006\u0010(\u001a\u00020\u0005H\u0002J \u0010)\u001a\u00020\"2\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\rH\u0002J\f\u0010,\u001a\u00020\u0005*\u00020\u0005H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/synthbyte/scanmate/utils/OcrPostProcessor;", "", "()V", "builtInDictionary", "Ljava/util/LinkedHashSet;", "", "domainSpacingRegex", "Lkotlin/text/Regex;", "emailSpacingRegex", "protectedTokenRegex", "tldAlternation", "wordRegex", "boundedEditDistance", "", "a", "b", "maxDistance", "buildDictionary", "", "customWords", "options", "Lcom/synthbyte/scanmate/utils/OcrPostProcessor$Options;", "confusionFold", "value", "correctDictionaryWords", "text", "dictionary", "correctToken", "token", "normalize", "raw", "normalizeDomain", "normalizeEmailSpacing", "markdownLinks", "", "normalizeUrlAndDomainSpacing", "preserveCapitalization", "original", "replacement", "sanitizeDictionaryWord", "word", "strongEnoughMatch", "candidate", "distance", "joinToStringPreservingParagraphs", "Options", "app_debug"})
public final class OcrPostProcessor {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.LinkedHashSet<java.lang.String> builtInDictionary = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String tldAlternation = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.text.Regex emailSpacingRegex = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.text.Regex domainSpacingRegex = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.text.Regex wordRegex = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.text.Regex protectedTokenRegex = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.utils.OcrPostProcessor INSTANCE = null;
    
    private OcrPostProcessor() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String normalize(@org.jetbrains.annotations.NotNull()
    java.lang.String raw, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> customWords, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.utils.OcrPostProcessor.Options options) {
        return null;
    }
    
    private final java.lang.String normalizeEmailSpacing(java.lang.String value, boolean markdownLinks) {
        return null;
    }
    
    private final java.lang.String normalizeUrlAndDomainSpacing(java.lang.String value) {
        return null;
    }
    
    private final java.lang.String normalizeDomain(java.lang.String value) {
        return null;
    }
    
    private final java.util.Set<java.lang.String> buildDictionary(java.util.Set<java.lang.String> customWords, com.synthbyte.scanmate.utils.OcrPostProcessor.Options options) {
        return null;
    }
    
    private final java.lang.String sanitizeDictionaryWord(java.lang.String word) {
        return null;
    }
    
    private final java.lang.String correctDictionaryWords(java.lang.String text, java.util.Set<java.lang.String> dictionary) {
        return null;
    }
    
    private final java.lang.String correctToken(java.lang.String token, java.util.Set<java.lang.String> dictionary) {
        return null;
    }
    
    private final boolean strongEnoughMatch(java.lang.String token, java.lang.String candidate, int distance) {
        return false;
    }
    
    private final java.lang.String confusionFold(java.lang.String value) {
        return null;
    }
    
    private final java.lang.String preserveCapitalization(java.lang.String original, java.lang.String replacement) {
        return null;
    }
    
    private final int boundedEditDistance(java.lang.String a, java.lang.String b, int maxDistance) {
        return 0;
    }
    
    private final java.lang.String joinToStringPreservingParagraphs(java.lang.String $this$joinToStringPreservingParagraphs) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0018"}, d2 = {"Lcom/synthbyte/scanmate/utils/OcrPostProcessor$Options;", "", "conservativeCorrection", "", "useBuiltInDictionary", "useCustomDictionary", "emailMarkdownLinks", "(ZZZZ)V", "getConservativeCorrection", "()Z", "getEmailMarkdownLinks", "getUseBuiltInDictionary", "getUseCustomDictionary", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"})
    public static final class Options {
        private final boolean conservativeCorrection = false;
        private final boolean useBuiltInDictionary = false;
        private final boolean useCustomDictionary = false;
        private final boolean emailMarkdownLinks = false;
        
        public Options(boolean conservativeCorrection, boolean useBuiltInDictionary, boolean useCustomDictionary, boolean emailMarkdownLinks) {
            super();
        }
        
        public final boolean getConservativeCorrection() {
            return false;
        }
        
        public final boolean getUseBuiltInDictionary() {
            return false;
        }
        
        public final boolean getUseCustomDictionary() {
            return false;
        }
        
        public final boolean getEmailMarkdownLinks() {
            return false;
        }
        
        public Options() {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.synthbyte.scanmate.utils.OcrPostProcessor.Options copy(boolean conservativeCorrection, boolean useBuiltInDictionary, boolean useCustomDictionary, boolean emailMarkdownLinks) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}