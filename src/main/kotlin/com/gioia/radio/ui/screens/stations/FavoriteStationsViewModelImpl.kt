package com.gioia.radio.ui.screens.stations

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.gioia.radio.data.repositories.ConfigurationRepository
import com.gioia.radio.data.repositories.RadioStationRepository
import com.gioia.radio.services.PlayerService

class FavoriteStationsViewModelImpl(
    playerService: PlayerService,
    radioStationsRepository: RadioStationRepository,
    configurationRepository: ConfigurationRepository,
    stateKeeper: StateKeeper,
) : StationsViewModelImpl(playerService, radioStationsRepository, configurationRepository, stateKeeper) {
    init {
        changeState { state.copy(
            radioStations = radioStationsRepository.getFavoritesRadioStations()
        )}

       // stateKeeper.register(this.javaClass.name) { state }
    }
}