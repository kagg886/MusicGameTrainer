package top.kagg886.mgt.common.ui.component

import androidx.annotation.OptIn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import top.kagg886.mgt.common.util.collectStateFromNoComposable
import java.io.File
import kotlin.math.abs

@OptIn(UnstableApi::class)
@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    file: File,
    state: VideoState,
    onDispose: () -> Unit
) {
    val ctx = LocalContext.current
    val manager = remember {
        ExoPlayer.Builder(ctx).build()
    }


    //与播放器有关
    DisposableEffect(Unit) {
        val item = MediaItem.fromUri(FileProvider.getUriForFile(ctx, "${ctx.packageName}.fileProvider", file))
        manager.addMediaItem(item)
        manager.prepare()
        onDispose {
            manager.release()
        }
    }

    var frameSecond by remember { mutableLongStateOf(-1L) }
    val currentPos by collectStateFromNoComposable(provider = { mutableLongStateOf(it) }) {
        manager.currentPosition
    }
    LaunchedEffect(currentPos) {
        frameSecond = abs(currentPos - state.value.offset)
        if (frameSecond > 55) {
            manager.seekTo(state.value.offset)
            state.value = state.value.copy(
                offset = state.value.offset,
                all = manager.duration
            )
            return@LaunchedEffect
        }
        state.value = state.value.copy(
            offset = currentPos,
            all = manager.duration
        )
    }

    LaunchedEffect(state.value.speed) {
        manager.setPlaybackSpeed(state.value.speed)
    }

    LaunchedEffect(state.value.playing) {
        if (state.value.playing) {
            manager.play()
        } else {
            manager.pause()
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { _ ->
            PlayerView(ctx).apply {
                player = manager
                useController = false
            }
        },
        update = {}
    )
}