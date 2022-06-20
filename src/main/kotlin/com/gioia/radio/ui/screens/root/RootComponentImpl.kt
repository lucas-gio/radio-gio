package com.gioia.radio.ui.screens.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gioia.radio.config.di
import com.gioia.radio.ui.screens.main.MainComponent
import com.gioia.radio.ui.screens.root.RootComponent.Child
import com.gioia.radio.ui.screens.settings.SettingsComponent
import org.kodein.di.instance

class RootComponentImpl(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val router = router<Config, Child>(
        initialConfiguration = Config.Radio,
        handleBackButton = true, // Pop the back stack on back button press
        childFactory = ::createScreenComponent
    )

    private sealed class Config : Parcelable {
        @Parcelize
        object Radio : Config()

        @Parcelize
        //data class Configuration(val itemId: Long) : Config() -->  así si la pantalla requiriese algún parámetro de filtro.
        object Search : Config()

        object Favorites : Config()

        @Parcelize
        object Settings : Config()
    }

    override val routerState: Value<RouterState<*, Child>> = router.state
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
        //router.push(Config.Settings)
    }

    private fun createScreenComponent(
        config: Config,
        componentContext: ComponentContext
    ): Child {
        return when (config) {
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

    @Composable
    override fun render() {
       Root(this)
    }
}