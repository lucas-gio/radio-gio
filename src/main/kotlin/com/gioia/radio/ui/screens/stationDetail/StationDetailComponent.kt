package com.gioia.radio.ui.screens.stationDetail

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.data.domains.RadioStation
import com.gioia.radio.ui.navigation.Component
import com.gioia.radio.ui.screens.stations.StationsViewModel

class StationDetailComponent(
    private val componentContext: ComponentContext,
    private val selectedStation: RadioStation
) : Component, ComponentContext by componentContext {
    @Composable
    override fun render() {
        StationDetail(
            selectedStation
        )
    }
}