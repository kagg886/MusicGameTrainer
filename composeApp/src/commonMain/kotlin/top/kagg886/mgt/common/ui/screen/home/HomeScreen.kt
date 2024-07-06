package top.kagg886.mgt.common.ui.screen.home

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.viewmodel.viewModel
import org.jetbrains.compose.resources.stringResource
import top.kagg886.mgt.common.LocalNavigation
import top.kagg886.mgt.common.Res
import top.kagg886.mgt.common.content_is_empty
import top.kagg886.mgt.common.ui.component.FailPage
import top.kagg886.mgt.common.ui.component.Loading
import top.kagg886.mgt.common.ui.icon.MusicVideo
import top.kagg886.mgt.common.ui.screen.import.IMPORT_ROUTE

@Composable
private fun HomeScreen() {
    val model = viewModel {
        HomeViewModel().apply {
            dispatch(HomeAction.StartFetching)
        }
    }
    val state by model.state.collectAsState()
    val nav = LocalNavigation.current
    when (state) {
        is HomeState.LoadFail -> {
            FailPage((state as HomeState.LoadFail).msg) {
                model.dispatch(HomeAction.StartFetching)
            }
        }

        is HomeState.LoadSuccess -> {
            Box(modifier = Modifier.fillMaxSize()) {
                val l = (state as HomeState.LoadSuccess).list
                if (l.isEmpty()) {
                    FailPage(stringResource(Res.string.content_is_empty)) {
                        model.dispatch(HomeAction.StartFetching)
                    }
                }

                if (l.isNotEmpty()) {
                    LazyColumn {
                        items(l) {
                            var isExpand by remember { mutableStateOf(false) }

                            ListItem(headlineContent = {
                                Text(it.name)
                            }, leadingContent = {
                                Icon(imageVector = MusicVideo, contentDescription = "")
                            }, supportingContent = {
                                Text("${it.config.lastOpenTime}")
                            }, modifier = Modifier.clickable {
                                isExpand = !isExpand
                            })

                            AnimatedVisibility(
                                visible = isExpand,
                                enter = slideInVertically { -it } + fadeIn(),
                                exit = slideOutVertically { -it } + fadeOut()
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    IconButton(
                                        onClick = {
                                            model.dispatch(HomeAction.StartPractice(it.root))
                                        }, colors = IconButtonDefaults.iconButtonColors().copy(
                                            contentColor = Color.Green
                                        )
                                    ) {
                                        Icon(imageVector = Icons.Outlined.PlayArrow, contentDescription = "")
                                    }
                                    IconButton(
                                        onClick = {
                                            model.dispatch(HomeAction.Delete(it))
                                        }, colors = IconButtonDefaults.iconButtonColors().copy(
                                            contentColor = Color.Red,
                                        )
                                    ) {
                                        Icon(imageVector = Icons.Outlined.Delete, contentDescription = "")
                                    }
                                }
                            }
                            HorizontalDivider()
                        }
                    }
                }

                FloatingActionButton(onClick = {
                    nav.navigate(IMPORT_ROUTE)
                }, modifier = Modifier.align(Alignment.BottomEnd).padding(20.dp)) {
                    Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "")
                }

            }
        }

        HomeState.Loading -> {
            Loading()
        }
    }
}

const val HOME_ROUTE = "home"

fun RouteBuilder.configureHomeScreen() {
    scene(HOME_ROUTE) {
        HomeScreen()
    }
}