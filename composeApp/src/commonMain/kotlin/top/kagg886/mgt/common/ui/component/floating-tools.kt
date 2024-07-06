package top.kagg886.mgt.common.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import org.jetbrains.compose.resources.stringResource
import top.kagg886.mgt.common.Res
import top.kagg886.mgt.common.choose_a_speed
import top.kagg886.mgt.common.lib.Practice
import top.kagg886.mgt.common.ui.icon.Pause
import top.kagg886.mgt.common.ui.icon.Speed
import kotlin.math.roundToLong

fun ItemDsl.putTools(show: Boolean, state: VideoState, p: Practice) {
    //playing button
    animItem(show) {
        IconButton(onClick = { state.isPlaying = !state.isPlaying }) {
            Icon(
                imageVector = if (state.isPlaying) Pause else Icons.Outlined.PlayArrow,
                contentDescription = ""
            )
        }
    }

    //speed choose
    animItem(show) {
        var dialog by remember {
            mutableStateOf(false)
        }
        var currentSpeed by remember {
            mutableFloatStateOf(p.config.speed)
        }

        if (dialog) {
            AlertDialog(onDismissRequest = { dialog = false }, confirmButton = {}, title = {
                Text("${stringResource(Res.string.choose_a_speed)}:${currentSpeed}x")
            }, text = {
                Slider(
                    value = currentSpeed,
                    onValueChange = {
                        currentSpeed = String.format("%.1f",it).toFloat()
                        state.speed = it
                        p.setConfig { copy(speed = it) }
                    },
                    valueRange = 0.3f..1f,
                    steps = 6,
                    modifier = Modifier.pointerInput(Unit) {
                        detectDragGestures(onDragEnd = {
                            dialog = false
                        }) { _, _ -> }
                    }
                )
            })
        }
        IconButton(onClick = {
            dialog = true
        }) {
            Icon(
                imageVector = Speed,
                contentDescription = ""
            )
        }
    }
    animItem(show) {
        IconButton(onClick = {
            state.seekBack((state.offset - 2000 * state.speed).roundToLong())
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                contentDescription = ""
            )
        }
    }

    animItem(show) {
        IconButton(onClick = {
            state.seekForward((2000 * state.speed).roundToLong())
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = ""
            )
        }
    }
}

private fun ItemDsl.animItem(show: Boolean, content: @Composable () -> Unit) {
    item {
        AnimatedVisibility(
            show, enter = fadeIn(), exit = fadeOut(), modifier = it
        ) { content() }
    }
}