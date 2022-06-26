package com.gioia.radio.ui.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gioia.radio.config.di
import com.gioia.radio.config.dk
import com.gioia.radio.ui.navigation.RootComponent.Child
import com.gioia.radio.ui.screens.main.MainComponent
import com.gioia.radio.ui.screens.root.Root
import com.gioia.radio.ui.screens.settings.SettingsComponent
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
        object Radio : Config()

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
            is Config.Welcome -> Child.Welcome(WelcomeComponent(
                componentContext = componentContext,
                onWelcomeFinished = ::onWelcomeFinished,
                welcomeViewModel = dk.instance()
            ))
            is Config.Radio -> Child.Radio(mainComponent(componentContext))
            is Config.Search -> Child.Search(mainComponent(componentContext))
            is Config.Favorites -> Child.Favorites(mainComponent(componentContext))
            is Config.Settings -> Child.Settings(configurationComponent(componentContext))
        }
    }

    private fun mainComponent(componentContext: ComponentContext): MainComponent {
        val component: MainComponent by di.instance()
        component.componentContext = componentContext
        component.onConfigPressed = { router.push(Config.Search) }
        return component
    }

    private fun configurationComponent(componentContext: ComponentContext): SettingsComponent {
        val component: SettingsComponent by di.instance()
        component.componentContext = componentContext
        component.onBackPressed = { router.pop() }
        return component
    }

    fun onWelcomeFinished(){//fixme llevar al viewModel
        router.replaceCurrent(Config.Radio)
    }

    @Composable
    override fun render() {
       Root(this)
    }

    override fun onRadioNavigationItem() {
        router.bringToFront(Config.Radio)
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