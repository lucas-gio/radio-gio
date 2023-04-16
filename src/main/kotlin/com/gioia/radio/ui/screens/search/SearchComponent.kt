package com.gioia.radio.ui.screens.search

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.ui.navigation.Component
import com.gioia.radio.ui.screens.stations.StationsViewModel

class SearchComponent(
    private val componentContext: ComponentContext,
    private val stationsViewModel: StationsViewModel,
) : Component, ComponentContext by componentContext {
    @Composable
    override fun render() {
        Search(
            componentContext = componentContext,
            stationsViewModel = stationsViewModel
        )
    }

}