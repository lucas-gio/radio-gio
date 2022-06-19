package com.gioia.radio.ui.screens.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gioia.radio.config.di
import com.gioia.radio.ui.screens.configuration.ConfigurationComponent
import com.gioia.radio.ui.screens.main.MainComponent
import com.gioia.radio.ui.screens.root.RootComponent.Child
import org.kodein.di.instance


class RootComponentImpl(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val router = router<ScreenConfig, Child>(
        initialConfiguration = ScreenConfig.Main,
        handleBackButton = true, // Pop the back stack on back button press
        childFactory = ::createScreenComponent
    )

    private sealed class ScreenConfig : Parcelable {
        @Parcelize
        object Main : ScreenConfig()

        @Parcelize
        //data class Configuration(val itemId: Long) : Config() -->  así si la pantalla requiriese algún parámetro de filtro.
        object Configuration : ScreenConfig()
    }

    override val routerState: Value<RouterState<*, Child>> = router.state

    private fun createScreenComponent(
        screenConfig: ScreenConfig,
        componentContext: ComponentContext
    ): Child {
        return when (screenConfig) {
            is ScreenConfig.Main -> Child.Main(mainScreen(componentContext))
            is ScreenConfig.Configuration -> Child.Configuration(configurationScreen(componentContext))
        }
    }

    private fun mainScreen(componentContext: ComponentContext): MainComponent {
        val component: MainComponent by di.instance()
        component.componentContext = componentContext
        component.onConfigPressed = { router.push(ScreenConfig.Configuration) }
        return component
    }

    private fun configurationScreen(componentContext: ComponentContext): ConfigurationComponent {
        val component: ConfigurationComponent by di.instance()
        component.componentContext = componentContext
        component.onBackPressed = { router.pop() }
        return component
    }

    @Composable
    override fun render() {
       Root(this)
    }
}