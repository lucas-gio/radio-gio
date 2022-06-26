package com.gioia.radio.ui.screens.root

import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.childAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.slide
import com.gioia.radio.ui.navigation.RootComponent
import com.gioia.radio.ui.screens.root.bottomAppBar.NavigationBottomBar
import com.gioia.radio.ui.screens.root.topAppBar.NavigationTopBar

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun Root(rootComponent: RootComponent) {
    Children(
        routerState = rootComponent.routerState,
        animation = childAnimation(slide())
    ) { child->
        Scaffold(
            topBar = {
                TopAppBar {
                    NavigationTopBar()
                }
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