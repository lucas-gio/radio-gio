package com.gioia.radio.ui.screens.settings

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.ui.navigation.Component

class SettingsComponent(
    private val componentContext: ComponentContext,
    private val settingsViewModel: SettingsViewModel,
) : Component, ComponentContext by componentContext {
    @Composable
    override fun render() {
        Settings(
            componentContext = componentContext,
            settingsViewModel = settingsViewModel
        )
    }

}