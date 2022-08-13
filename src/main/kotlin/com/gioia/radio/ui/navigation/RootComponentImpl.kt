package com.gioia.radio.ui.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gioia.radio.config.dk
import com.gioia.radio.ui.navigation.RootComponent.Child
import com.gioia.radio.ui.screens.settings.SettingsComponent
import com.gioia.radio.ui.screens.stations.StationsComponent
import com.gioia.radio.ui.screens.welcome.WelcomeComponent
import org.kodein.di.instance

class RootComponentImpl(
    componentContext: ComponentContext
) : Component, RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        initialConfiguration = Config.Welcome,
        handleBackButton = true,
        source = navigation,
        childFactory = ::createScreenComponent)

    override val childStack: Value<ChildStack<*, Child>> get() = stack

    /**
     * Available screens to select
     */
    private sealed class Config : Parcelable {

        @Parcelize
        object Welcome : Config()

        @Parcelize
        object Stations : Config()

        @Parcelize
        //data class Configuration(val itemId: Long) : Config() -->  así si la pantalla requiriese algún parámetro de filtro.
        object Search : Config()

        @Parcelize
        object Favorites : Config()

        @Parcelize
        object Settings : Config()
    }

    /**
     * Called when a new navigation request made.
     */
    private fun createScreenComponent(
        config: Config,
        componentContext: ComponentContext
    ): Child {
        return when (config) {
            is Config.Welcome -> Child.Welcome(
                WelcomeComponent(
                    componentContext = componentContext,
                    onWelcomeFinished = {navigation.bringToFront(Config.Stations)},
                    welcomeViewModel = dk.instance()
                )
            )
            is Config.Stations -> Child.Stations(
                StationsComponent(
                    componentContext = componentContext,
                    stationsViewModel = dk.instance(),
                    false
                )
            )
            is Config.Search -> Child.Search(
                StationsComponent(
                    componentContext = componentContext,
                    stationsViewModel = dk.instance(),
                    false
                )
            )
            is Config.Favorites -> Child.Favorites(
                StationsComponent(
                    componentContext = componentContext,
                    stationsViewModel = dk.instance(tag = "favorites"),
                    true
                )
            )
            is Config.Settings -> Child.Settings(
                SettingsComponent(
                    componentContext = componentContext,
                    settingsViewModel = dk.instance()
                )
            )
        }
    }

    @Composable
    override fun render() {
       Root(this)
    }

    override fun onRadioNavigationItem() {
        navigation
        navigation.bringToFront(Config.Stations)
    }

    override fun onSearchNavigationItem() {
        navigation.bringToFront(Config.Search)
    }

    override fun onFavoriteNavigationItem() {
        navigation.bringToFront(Config.Favorites)
    }

    override fun onSettingsNavigationItem() {
        navigation.bringToFront(Config.Settings)
    }
}