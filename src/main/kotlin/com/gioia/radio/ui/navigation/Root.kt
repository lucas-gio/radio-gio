package com.gioia.radio.ui.navigation

import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.childAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.slide
import com.gioia.radio.config.dk
import com.gioia.radio.ui.screens.bottomAppBar.NavigationBottomBar
import com.gioia.radio.ui.screens.topAppBar.NavigationTopBar
import org.kodein.di.instance

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
                    NavigationTopBar(dk.instance())
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