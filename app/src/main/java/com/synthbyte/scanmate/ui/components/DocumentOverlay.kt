package com.synthbyte.scanmate.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect

/**
 * Draws an animated document quadrilateral from normalized corner offsets in the [0f, 1f] range.
 */
@Composable
fun DocumentOverlay(corners: List<Offset>?, confidence: Float = 0f) {
    if (corners == null || corners.size != 4) return

    val infiniteTransition = rememberInfiniteTransition(label = "scan_pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(800, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "pulse"
    )
    val cornerScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(tween(600, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "corner_scale"
    )
    val isLocked = confidence >= 0.75f
    val animAlpha by animateFloatAsState(if (isLocked) 1f else pulseAlpha, animationSpec = tween(300), label = "overlay_alpha")
    val strokeEffect = if (isLocked) null else PathEffect.dashPathEffect(floatArrayOf(14f, 7f), phase = 0f)
    val overlayColor = if (isLocked) Color(0xFF00E676) else Color(0xFF64B5F6)
    val tl by animateOffsetAsState(corners[0].coercedUnit(), animationSpec = spring(stiffness = Spring.StiffnessMediumLow), label = "doc_tl")
    val tr by animateOffsetAsState(corners[1].coercedUnit(), animationSpec = spring(stiffness = Spring.StiffnessMediumLow), label = "doc_tr")
    val br by animateOffsetAsState(corners[2].coercedUnit(), animationSpec = spring(stiffness = Spring.StiffnessMediumLow), label = "doc_br")
    val bl by animateOffsetAsState(corners[3].coercedUnit(), animationSpec = spring(stiffness = Spring.StiffnessMediumLow), label = "doc_bl")

    Box(Modifier.fillMaxSize()) {
        Canvas(Modifier.fillMaxSize()) {
            fun Offset.toCanvasOffset(): Offset = Offset(x * size.width, y * size.height)
            val path = Path().apply {
                moveTo(tl.toCanvasOffset().x, tl.toCanvasOffset().y)
                lineTo(tr.toCanvasOffset().x, tr.toCanvasOffset().y)
                lineTo(br.toCanvasOffset().x, br.toCanvasOffset().y)
                lineTo(bl.toCanvasOffset().x, bl.toCanvasOffset().y)
                close()
            }
            // 1. Semi-transparent fill
            drawPath(path, color = overlayColor.copy(alpha = animAlpha * if (isLocked) 0.18f else 0.08f))

            // 2. Animated stroke
            drawPath(
                path = path,
                color = overlayColor.copy(alpha = animAlpha),
                style = Stroke(
                    width = (if (isLocked) 3.5f else 2.5f).dp.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                    pathEffect = strokeEffect
                )
            )

            // 3. Corner accent dots — scale pulse when not locked
            listOf(tl, tr, br, bl).forEach { c ->
                val pt = c.toCanvasOffset()
                val r = (if (isLocked) 7f else 6f * cornerScale).dp.toPx()
                drawCircle(Color.White, radius = r + 2.dp.toPx(), center = pt, style = Stroke(2.dp.toPx()))
                drawCircle(overlayColor.copy(alpha = animAlpha), radius = r, center = pt)
            }
        }
    }
}

private fun Offset.coercedUnit(): Offset = Offset(x.coerceIn(0f, 1f), y.coerceIn(0f, 1f))
