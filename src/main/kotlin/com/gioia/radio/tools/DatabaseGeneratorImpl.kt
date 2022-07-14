package com.gioia.radio.tools

import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.RadioStation
import com.gioia.radio.data.repositories.ConfigurationRepository
import com.gioia.radio.data.repositories.CountryRepository
import com.gioia.radio.data.repositories.RadioStationRepository
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.InputStreamReader
import java.io.Reader

class DatabaseGeneratorImpl (
    private val countryRepository: CountryRepository,
    private val radioStationRepository: RadioStationRepository,
    configurationRepository: ConfigurationRepository,
) : DatabaseGenerator(
    configurationRepository
) {
    private var logger: Logger = LoggerFactory.getLogger(DatabaseGeneratorImpl::class.java)
    private val nameField = "1"
    private val countryField = "4"
    private val urlField = "6"

    private fun initCountries(countries: List<Country>){
        countryRepository.removeAll()
        countryRepository.saveAll(countries)
        countryRepository.createIndexes()
    }

    private fun initRadioStations(radioStations: List<RadioStation>) {
        radioStationRepository.removeAll()
        radioStationRepository.saveAll(radioStations)
        radioStationRepository.createIndexes()
    }

    override fun parseDataFile() {
        val reader: Reader = InputStreamReader(DatabaseGeneratorImpl::class.java.classLoader.getResourceAsStream("radios.json"))
        val stations = Gson().fromJson(reader, MutableList::class.java) as List<Map<String,String>>
        val countries = mutableListOf<Country>()
        val radioStations = mutableListOf<RadioStation>()
        var country: Country

        for(station in stations){
            station[countryField]?.let{
                country = Country(
                    code = it
                )

                if(!countries.contains(country)) {
                    countries.add(country)
                }
            }

            if (! (station[urlField].isNullOrEmpty() || station[nameField].isNullOrEmpty())){
                radioStations.add(
                    RadioStation(
                        name = station[nameField] !!,
                        description = station["2"],
                        category = station["3"],
                        countryCode = station[countryField] !!,
                        language = station["5"],
                        url = station[urlField] !!,
                        url2 = station["7"],
                        url3 = station["8"],
                        url4 = station["9"],
                        url5 = station["10"],
                        isFavourite = false
                    )
                )
            }
        }

        initCountries(countries)
        initRadioStations(radioStations)
    }
}