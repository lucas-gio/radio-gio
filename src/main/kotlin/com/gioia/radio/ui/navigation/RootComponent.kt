package com.gioia.radio.ui.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RootComponent: Component {
    val childStack: Value<ChildStack<*, Child>>
    fun onRadioNavigationItem()
    fun onSearchNavigationItem()
    fun onFavoriteNavigationItem()
    fun onSettingsNavigationItem()

    sealed class Child {
        abstract val component: Component
        class Welcome(override val component: Component) : Child()
        class Stations(override val component: Component) : Child()
        class Search(override val component: Component) : Child()
        class Favorites(override val component: Component) : Child()
        class Settings(override val component: Component) : Child()
    }
}