package com.gioia.radio.ui.screens.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value

interface RootComponent: Component {
    val routerState: Value<RouterState<*, Child>>
    fun onRadioNavigationItem()
    fun onSearchNavigationItem()
    fun onFavoriteNavigationItem()
    fun onSettingsNavigationItem()

    sealed class Child {
        abstract val component: Component
        class Radio(override val component: Component) : Child()
        class Search(override val component: Component) : Child()
        class Favorites(override val component: Component) : Child()
        class Settings(override val component: Component) : Child()
    }
}