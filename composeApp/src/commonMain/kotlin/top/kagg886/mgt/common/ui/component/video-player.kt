package top.kagg886.mgt.common.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.io.File
import kotlin.math.max
import kotlin.math.min

typealias VideoState = MutableState<InternalVideoState>


data class InternalVideoState(
    val offset: Long,
    val all: Long,
    val speed: Float = 1f,
    val playing: Boolean = false,
)

val VideoState.offset: Long
    get() = value.offset

var VideoState.speed
    get() = value.speed
    set(value) {
        this.value = this.value.copy(speed = value)
    }

var VideoState.isPlaying
    get() = value.playing
    set(value) {
        this.value = this.value.copy(playing = value)
    }


fun VideoState.seekBack(pos: Long) {
    value = value.copy(
        offset = value.offset - max(0, pos),
    )
}

fun VideoState.seekForward(pos: Long) {
    value = value.copy(
        offset = value.offset + min(pos, value.all),
    )
}

@Composable
fun rememberVideoState(): VideoState = remember {
    mutableStateOf(
        InternalVideoState(
            offset = 0,
            all = 0
        )
    )
}

@Composable
expect fun VideoPlayer(
    modifier: Modifier = Modifier,
    file: File,
    state: VideoState,
    onDispose: () -> Unit = {}
)