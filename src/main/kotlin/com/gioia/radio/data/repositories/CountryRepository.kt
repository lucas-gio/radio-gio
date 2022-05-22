package com.gioia.radio.data.repositories

import com.gioia.radio.data.domains.Country

interface CountryRepository {
    fun removeAll()
    fun saveAll(countries:List<Country>)
    fun createIndexes()
    fun getInitialRadioStations(): List<Country>

    fun findByCountryNameLike(countryName: String): List<Country>
    fun findByRadioNameLike(radioName: String): List<Country>
}