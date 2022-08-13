package com.gioia.radio.ui.navigation

import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
                  //  content = {  NavigationTopBar(dk.instance(), dk.instance()) }
                )
            },
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