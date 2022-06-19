package com.gioia.radio.ui.screens.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.childAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.slide

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun Root(rootComponent: RootComponent) {
    Children(
        routerState = rootComponent.routerState,
        animation = childAnimation(slide())
    ) {
        it.instance.component.render()
    }
}