package com.gioia.radio.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.gioia.radio.config.dk
import com.gioia.radio.ui.screens.bottomAppBar.NavigationBottomBar
import com.gioia.radio.ui.screens.topAppBar.NavigationTopBar
import org.kodein.di.instance

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun Root(rootComponent: RootComponent) {
    val stack by rootComponent.childStack.subscribeAsState()

    Children(
        stack = stack,
        modifier = Modifier,
       // animation = StackAnimation<Children, Stack>()
    ) { child->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { NavigationTopBar(dk.instance(), dk.instance()) },
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    shape = RoundedCornerShape(45.dp),
                    modifier = Modifier.size(80.dp),
                    onClick = {}, //stationsViewModel::onStopPressed,
                    //enabled = state.isPlaying && state.selectedRadioStation != null,
                ) {
                    Icon(
                        imageVector = Icons.Default.Stop,
                        contentDescription = "Stop",
                        modifier = Modifier.size(35.dp)
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            bottomBar = {
                BottomAppBar {
                    NavigationBottomBar(rootComponent)
                }
            }
        ) {
            child.instance.component.render()
        }
    }
}