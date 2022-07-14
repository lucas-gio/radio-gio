package com.gioia.radio.data.repositories

import com.gioia.radio.data.domains.Country

interface CountryRepository {
    fun removeAll()
    fun saveAll(countries:List<Country>)
    fun createIndexes()
    fun setFavourite(countryName: String, radioName: String)
}