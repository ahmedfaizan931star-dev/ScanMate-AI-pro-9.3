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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect

/**
 * Draws an animated document quadrilateral from normalized corner offsets in the [0f, 1f] range.
 */
@Composable
fun DocumentOverlay(corners: List<Offset>?, confidence: Float = 0f) {
    if (corners == null || corners.size != 4) return

    val isLocked = confidence >= 0.75f
    val animAlpha by animateFloatAsState(if (isLocked) 1f else 0.55f, animationSpec = tween(300), label = "overlay_alpha")
    val strokeEffect = if (isLocked) null else PathEffect.dashPathEffect(floatArrayOf(14f, 7f), 0f)
    val fillColor = Color(0xFF00E676).copy(alpha = animAlpha * 0.15f)
    val strokeColor = Color(0xFF00E676).copy(alpha = animAlpha)
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
            drawPath(path, color = fillColor)
            drawPath(
                path = path,
                color = strokeColor,
                style = Stroke(
                    width = 3.dp.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                    pathEffect = strokeEffect
                )
            )
            listOf(tl, tr, br, bl).forEach { corner ->
                val canvas = corner.toCanvasOffset()
                drawCircle(Color.White, radius = 7.dp.toPx(), center = canvas, style = Stroke(2.dp.toPx()))
                drawCircle(strokeColor, radius = 4.dp.toPx(), center = canvas)
            }
        }
    }
}

private fun Offset.coercedUnit(): Offset = Offset(x.coerceIn(0f, 1f), y.coerceIn(0f, 1f))
