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

/**
 * Draws an animated document quadrilateral from normalized corner offsets in the [0f, 1f] range.
 */
@Composable
fun DocumentOverlay(corners: List<Offset>?) {
    if (corners == null || corners.size != 4) return

    val outlineColor = MaterialTheme.colorScheme.primary
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
            drawPath(
                path = path,
                color = outlineColor,
                style = Stroke(
                    width = 3.dp.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }
    }
}

private fun Offset.coercedUnit(): Offset = Offset(x.coerceIn(0f, 1f), y.coerceIn(0f, 1f))
