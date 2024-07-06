package top.kagg886.mgt.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import moe.tlaster.precompose.PreComposeApp
import top.kagg886.mgt.common.lib.Practice
import top.kagg886.mgt.common.ui.component.*

@Composable
fun VideoApp(p: Practice) {
    PreComposeApp {
        MaterialTheme {
            val state = rememberVideoState()
            var state0 by state
            LaunchedEffect(Unit) {
                state0 = state0.copy(
                    playing = true,
                    speed = p.config.speed
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                VideoPlayer(modifier = Modifier.fillMaxSize(1f), p.videoFile, state = state)
                var offset by remember { mutableStateOf(Offset.Zero) }
                var showDetails by remember { mutableStateOf(false) }
                FloatingIcon(offset = offset, onDrag = {
                    offset += it
                }) {
                    Surround(
                        mainView = {
                            IconToggleButton(modifier = it, checked = showDetails, onCheckedChange = {
                                showDetails = !showDetails
                            }) {
                                val rotate by animateFloatAsState(if (showDetails) 1f else 360f)
                                AnimatedContent(
                                    targetState = showDetails,
                                    transitionSpec = { (fadeIn()).togetherWith(fadeOut()) }) { state ->
                                    if (state) {
                                        Icon(
                                            modifier = Modifier.rotate(rotate),
                                            imageVector = Icons.Outlined.Close,
                                            contentDescription = ""
                                        )
                                    } else {
                                        Icon(
                                            modifier = Modifier.rotate(rotate),
                                            imageVector = Icons.Outlined.Menu,
                                            contentDescription = ""
                                        )
                                    }
                                }
                            }
                        }
                    ) {
                        putTools(showDetails, state, p)
                    }
                }
            }
        }

    }
}