package top.kagg886.mgt.common.util

import androidx.compose.ui.unit.IntOffset
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

fun IntOffset.rotate(center: IntOffset, rad: Double): IntOffset {
    val (x0, y0) = center
    val (x1, y1) = this

    return IntOffset(
        x = ((x1 - x0) * cos(rad) - (y1 - y0) * sin(rad) + x0).roundToInt(),
        y = ((y1 - y0) * cos(rad) + y0 + (x1 - x0) * sin(rad) + y0).roundToInt()
    )
}