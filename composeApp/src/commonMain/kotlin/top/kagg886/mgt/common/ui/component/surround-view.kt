package top.kagg886.mgt.common.ui.component

import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.IntOffset
import top.kagg886.mgt.common.util.rotate
import kotlin.math.roundToInt

class ItemDsl {
    val items = mutableListOf<@Composable (Modifier) -> Unit>()
    fun item(i: @Composable (Modifier) -> Unit) {
        items.add(i)
    }
}

@Composable
fun Surround(
    distance: Int = 100,
    mainView: @Composable (Modifier) -> Unit,
    surround: ItemDsl.() -> Unit
) {
    val items = ItemDsl().apply(surround).items

    var centerPos by remember {
        mutableStateOf(IntOffset.Zero)
    }

    mainView(Modifier.onGloballyPositioned {
        centerPos = with(it.positionInRoot()) {
            IntOffset(x.roundToInt(), y.roundToInt())
        }
    })

    items.withIndex().forEach { (index, item) ->
        item(Modifier.absoluteOffset {
            val rad = 2 * Math.PI / items.size * (index + 1)
            val targetPos = centerPos.copy(x = centerPos.x + distance)

            (targetPos - centerPos).rotate(IntOffset.Zero,rad)
        })
    }
}