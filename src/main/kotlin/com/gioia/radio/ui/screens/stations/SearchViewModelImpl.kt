package com.gioia.radio.ui.screens.stations

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.gioia.radio.data.repositories.ConfigurationRepository
import com.gioia.radio.data.repositories.CountryRepository
import com.gioia.radio.data.repositories.RadioStationRepository
import com.gioia.radio.services.PlayerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SearchViewModelImpl(
    playerService: PlayerService,
    radioStationsRepository: RadioStationRepository,
    configurationRepository: ConfigurationRepository,
    countriesRepository: CountryRepository,
    stateKeeper: StateKeeper,
) : StationsViewModelImpl(playerService, radioStationsRepository, configurationRepository, stateKeeper) {
    init {
        changeState { state.copy(
            countries = countriesRepository.findAllCountries()
        )}

        //stateKeeper.register(this.javaClass.name) { state }
    }
}