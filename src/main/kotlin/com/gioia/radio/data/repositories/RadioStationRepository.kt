package com.gioia.radio.data.repositories

import com.gioia.radio.data.domains.RadioStation

interface RadioStationRepository {
    fun removeAll()
    fun saveAll(radioStation: List<RadioStation>)
    fun createIndexes()
    fun getInitialRadioStations(): List<RadioStation>
    fun findByCountryNameLike(countryCode: String): List<RadioStation>
    fun findByRadioNameLike(radioName: String): List<RadioStation>
}