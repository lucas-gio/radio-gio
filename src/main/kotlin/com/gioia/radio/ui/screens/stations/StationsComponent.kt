package com.gioia.radio.ui.screens.stations

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.data.domains.RadioStation
import com.gioia.radio.ui.navigation.Component

class StationsComponent(
    private val componentContext: ComponentContext,
    private val stationsViewModel: StationsViewModel,
    private val onlyFavorites: Boolean,
    private val whenDetails: (RadioStation) -> Unit
) : Component, ComponentContext by componentContext {
    @Composable
    override fun render() {
        Stations(stationsViewModel, whenDetails)
    }
}