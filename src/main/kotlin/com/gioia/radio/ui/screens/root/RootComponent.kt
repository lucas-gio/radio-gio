package com.gioia.radio.ui.screens.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value

interface RootComponent: Component {
    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        abstract val component: Component
        class Main(override val component: Component) : Child()
        class Configuration(override val component: Component) : Child()
    }
}