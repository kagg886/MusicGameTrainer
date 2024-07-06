package top.kagg886.mgt.common.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import moe.tlaster.precompose.viewmodel.viewModel
import top.kagg886.mgt.common.util.collectStateFromNoComposable
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent
import java.io.File
import kotlin.math.abs


@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    file: File,
    state: VideoState,
    onDispose: () -> Unit
) {
    val model = viewModel {
        VideoPlayerDesktopModel().apply {
            dispatch(VideoPlayerVMAction.StartLoading)
        }
    }
    val modelState by model.state.collectAsState()

    when (modelState) {
        VideoPlayerVMState.LoadFail -> {
            FailPage(msg = "error!") {
                model.dispatch(VideoPlayerVMAction.StartLoading)
            }
        }

        VideoPlayerVMState.LoadSuccess -> {
            val player = remember { EmbeddedMediaPlayerComponent() }
            val surface = remember {
                SkiaBitmapVideoSurface().also {
                    player.mediaPlayer().videoSurface().set(it)
                }
            }

            DisposableEffect(Unit) {
                player.mediaPlayer().media().play(file.absolutePath)
                player.mediaPlayer().controls().pause()
                onDispose(player::release)
            }

            var frameSecond by remember { mutableLongStateOf(-1L) }
            val currentPos by collectStateFromNoComposable(provider = { mutableLongStateOf(it) }) {
                player.mediaPlayer().status().time()
            }
            LaunchedEffect(currentPos) {
                frameSecond = abs(currentPos - state.value.offset)
                if (frameSecond > 1000) {
                    player.mediaPlayer().controls().setTime(state.value.offset)
                    state.value = state.value.copy(
                        offset = state.value.offset,
                        all = player.mediaPlayer().media().info().duration()
                    )
                    return@LaunchedEffect
                }
                state.value = state.value.copy(
                    offset = currentPos,
                    all = player.mediaPlayer().media().info().duration()
                )
            }

            LaunchedEffect(state.value.speed) {
                player.mediaPlayer().controls().setRate(state.value.speed)
            }

            LaunchedEffect(state.value.playing) {
                player.mediaPlayer().controls().setPause(!state.value.playing)
            }

            surface.bitmap.value?.let { bitmap ->
                Image(
                    bitmap,
                    modifier = modifier
                        .background(Color.Transparent)
                        .fillMaxSize(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center,
                )
            }
        }

        VideoPlayerVMState.Loading -> Loading()
    }
}