package com.gioia.radio.ui.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gioia.radio.config.dk
import com.gioia.radio.data.domains.RadioStation
import com.gioia.radio.ui.navigation.RootComponent.Child
import com.gioia.radio.ui.screens.search.SearchComponent
import com.gioia.radio.ui.screens.settings.SettingsComponent
import com.gioia.radio.ui.screens.stationDetail.StationDetailComponent
import com.gioia.radio.ui.screens.stations.StationsComponent
import com.gioia.radio.ui.screens.welcome.WelcomeComponent
import org.kodein.di.instance

/*
 More info in: https://arkivanov.github.io/Decompose/navigation/stack/overview/
*/
class RootComponentImpl(
    componentContext: ComponentContext
) : Component, RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val _childStack = childStack(
        source = navigation,
        initialConfiguration = Config.Welcome,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override val childStack: Value<ChildStack<*, Child>> = _childStack

    /**
     * Available screens to select
     */
    sealed class Config : Parcelable {

        @Parcelize
        object Welcome : Config()

        @Parcelize
        object Stations : Config()

        @Parcelize
        data class StationDetail(val selectedStation: RadioStation) : Config()

        @Parcelize
        object Search : Config()

        @Parcelize
        object Favorites : Config()

        @Parcelize
        object Settings : Config()
    }

    /**
     * Called when a new navigation request made.
     */
    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): Child {
        return when (config) {
            is Config.Welcome -> Child.Welcome(
                WelcomeComponent(
                    componentContext = componentContext,
                    onWelcomeFinished = { navigation.bringToFront(Config.Stations) },
                    welcomeViewModel = dk.instance()
                )
            )
            is Config.Stations -> Child.Stations(
                StationsComponent(
                    componentContext = componentContext,
                    stationsViewModel = dk.instance(),
                    false,
                    whenDetails = { it: RadioStation -> navigation.push(Config.StationDetail(selectedStation = it)) }
                )
            )
            is Config.StationDetail -> Child.StationDetail(
                StationDetailComponent(
                    componentContext = componentContext,
                    selectedStation = config.selectedStation,
                    onFinished = { navigation.pop() },
                )
            )
            is Config.Search -> Child.Search(
                SearchComponent(
                    componentContext = componentContext,
                    stationsViewModel = dk.instance(tag = "search")
                )
            )
            is Config.Favorites -> Child.Favorites(
                StationsComponent(
                    componentContext = componentContext,
                    stationsViewModel = dk.instance(tag = "favorites"),
                    true,
                    whenDetails = {}
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
        navigation.bringToFront(Config.Stations)
    }

    override fun onStationDetail(radioStation: RadioStation) {
        navigation.push(
            Config.StationDetail(radioStation)
        )
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