package top.kagg886.mgt.common.ui.component

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Composable
fun FloatingIcon(
    modifier: Modifier = Modifier, offset: Offset, //一定是像素offset
    onDrag: (Offset) -> Unit, content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier
        .offset {
            IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
        }
        .pointerInput(Unit) {
            detectDragGestures(onDrag = { _, o ->
                onDrag(o)
            })
        }) {
        content(this)
    }
}