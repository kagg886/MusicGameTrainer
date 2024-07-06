package top.kagg886.mgt.common

import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import top.kagg886.mgt.common.ui.screen.home.HOME_ROUTE
import top.kagg886.mgt.common.ui.screen.home.configureHomeScreen
import top.kagg886.mgt.common.ui.screen.import.configureImportScreen
import top.kagg886.mgt.common.ui.screen.options.OPTIONS_ROUTE
import top.kagg886.mgt.common.ui.screen.options.configureOptionsScreen
import top.kagg886.mgt.common.util.DeviceType
import top.kagg886.mgt.common.util.getDeviceType

val LocalNavigation = compositionLocalOf<Navigator> {
    error("LocalNavigation not provided")
}

private val channel: Channel<String> = Channel()

suspend fun sendSnackBar(msg: String) {
    channel.send(msg)
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        PreComposeApp {
            CompositionLocalProvider(
                LocalNavigation provides rememberNavigator()
            ) {
                val nav = LocalNavigation.current
                val entry by nav.currentEntry.collectAsState(initial = null)
                val route = remember(entry) {
                    entry?.path ?: HOME_ROUTE
                }

                val snack = remember {
                    SnackbarHostState()
                }
                val scope = rememberCoroutineScope()
                scope.launch {
                    while (channel.isClosedForReceive.not()) {
                        val str = kotlin.runCatching {
                            channel.receive()
                        }.getOrNull() ?: continue
                        snack.showSnackbar(str)
                    }
                }
                Surface(color = MaterialTheme.colorScheme.background) {
                    val a = getDeviceType()
                    when (a) {
                        DeviceType.DESKTOP -> {
                            Row {
                                NavigationRail {
                                    NavigationRailItem(
                                        selected = route == HOME_ROUTE,
                                        onClick = {
                                            if (route != HOME_ROUTE) {
                                                nav.navigate(HOME_ROUTE)
                                            }
                                        },
                                        icon = {
                                            Icon(imageVector = Icons.Outlined.Home, contentDescription = "")
                                        },
                                        label = {
                                            Text(stringResource(Res.string.home))
                                        }
                                    )
                                    NavigationRailItem(
                                        selected = route == OPTIONS_ROUTE,
                                        onClick = {
                                            if (route != OPTIONS_ROUTE) {
                                                nav.navigate(OPTIONS_ROUTE)
                                            }
                                        },
                                        icon = {
                                            Icon(imageVector = Icons.Outlined.Settings, contentDescription = "")
                                        },
                                        label = {
                                            Text(stringResource(Res.string.setting))
                                        }
                                    )
                                }
                                Scaffold(
                                    snackbarHost = { SnackbarHost(hostState = snack) },
                                ) {
                                    NavContainer(modifier = Modifier.padding(it))
                                }
                            }

                        }

                        DeviceType.ANDROID -> {
                            Scaffold(
                                snackbarHost = { SnackbarHost(hostState = snack) },
                                bottomBar = {
                                    NavigationBar {
                                        NavigationBarItem(
                                            selected = route == HOME_ROUTE,
                                            onClick = {
                                                if (route != HOME_ROUTE) {
                                                    nav.navigate(HOME_ROUTE)
                                                }
                                            },
                                            icon = {
                                                Icon(imageVector = Icons.Outlined.Home, contentDescription = "")
                                            },
                                            label = {
                                                Text(stringResource(Res.string.home))
                                            }
                                        )
                                        NavigationBarItem(
                                            selected = route == OPTIONS_ROUTE,
                                            onClick = {
                                                if (route != OPTIONS_ROUTE) {
                                                    nav.navigate(OPTIONS_ROUTE)
                                                }
                                            },
                                            icon = {
                                                Icon(imageVector = Icons.Outlined.Settings, contentDescription = "")
                                            },
                                            label = {
                                                Text(stringResource(Res.string.setting))
                                            }
                                        )
                                    }
                                }
                            ) {
                                NavContainer(modifier = Modifier.padding(it))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NavContainer(modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navigator = LocalNavigation.current,
        navTransition = remember {
            NavTransition(
                createTransition = slideInHorizontally { it / 2 },
                destroyTransition = scaleOut(targetScale = 0.9f) + fadeOut(),
                pauseTransition = scaleOut(targetScale = 0.9f) + fadeOut(),
                resumeTransition = slideInHorizontally { it / 2 }
            )
        },
        initialRoute = HOME_ROUTE
    ) {
        configureHomeScreen()
        configureOptionsScreen()
        configureImportScreen()
    }
}
