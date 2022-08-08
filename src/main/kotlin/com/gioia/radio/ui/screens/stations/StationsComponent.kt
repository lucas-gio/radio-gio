package com.gioia.radio.ui.screens.stations

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.config.dk
import com.gioia.radio.ui.navigation.Component
import org.kodein.di.instance

class StationsComponent(
    private val componentContext: ComponentContext,
    private val stationsViewModel: StationsViewModel,
    private val onlyFavorites: Boolean
) : Component, ComponentContext by componentContext {
    @Composable
    override fun render() {
        Stations(
            componentContext,
            dk.instance(),
            stationsViewModel,
            onlyFavorites
        )
    }
}