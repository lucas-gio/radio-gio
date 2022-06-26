package com.gioia.radio.ui.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.bringToFront
import com.arkivanov.decompose.router.replaceCurrent
import com.arkivanov.decompose.router.router
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

    private val router = router<Config, Child>(
        initialConfiguration = Config.Welcome,
        handleBackButton = true, // Pop the back stack on back button press
        childFactory = ::createScreenComponent
    )

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

    //private val appComponent: AppComponent = DaggerAppComponent
    //    .create()

    override val routerState: Value<RouterState<*, Child>> = router.state

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
                    onWelcomeFinished = {router.replaceCurrent(Config.Stations)},
                    welcomeViewModel = dk.instance()
                )
            )
            is Config.Stations -> Child.Stations(
                StationsComponent(
                    componentContext = componentContext,
                    stationsViewModel = dk.instance()
                )
            ) //fixme: Pasar a la forma de WelcomeComponent, de un componente y viewModel
            is Config.Search -> Child.Search(
                StationsComponent(
                    componentContext = componentContext,
                    stationsViewModel = dk.instance()
                )
            )
            is Config.Favorites -> Child.Favorites(
                StationsComponent(
                    componentContext = componentContext,
                    stationsViewModel = dk.instance()
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
        router.bringToFront(Config.Stations)
    }

    override fun onSearchNavigationItem() {
        router.bringToFront(Config.Search)
    }

    override fun onFavoriteNavigationItem() {
        router.bringToFront(Config.Favorites)
    }

    override fun onSettingsNavigationItem() {
        router.bringToFront(Config.Settings)
    }
}