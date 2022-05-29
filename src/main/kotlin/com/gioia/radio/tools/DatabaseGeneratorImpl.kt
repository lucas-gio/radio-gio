package com.gioia.radio.tools

import com.gioia.radio.data.domains.Configuration
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.data.enums.ConfigKey
import com.gioia.radio.data.enums.Locales
import com.gioia.radio.data.repositories.ConfigurationRepository
import com.gioia.radio.data.repositories.CountryRepository
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.InputStreamReader
import java.io.Reader

class DatabaseGeneratorImpl (
    private val countryRepository: CountryRepository,
    private val configurationRepository: ConfigurationRepository,
) : DatabaseGenerator{
    private var logger: Logger = LoggerFactory.getLogger(DatabaseGeneratorImpl::class.java)
    override fun generateDatabase() {
        initCountries()
        initConfigurations()
    }

    private fun initConfigurations(){
        listOf(
            Configuration(ConfigKey.Locale.toString(), Locales.EN.toString(), "Selected locale for translations"),
            Configuration(ConfigKey.Volume.toString(), "50", "Last volume used"),
        )
        .forEach(configurationRepository::upsert)

        configurationRepository.createIndexes()
    }

    private fun initCountries(){
        countryRepository.removeAll()
        countryRepository.saveAll(parseStationsJsonToList())
        countryRepository.createIndexes()
    }

    private fun parseStationsJsonToList(): List<Country> {
        val gson = Gson()
        val reader: Reader = InputStreamReader(DatabaseGeneratorImpl::class.java.classLoader.getResourceAsStream("radios.json"))
        val stations: List<Map<String, String>> = gson.fromJson(reader, MutableList::class.java) as List<Map<String,String>>
        val countriesAndRadios: HashMap<String, List<Map<String, String>>> = HashMap()

        for(station in stations){
            if( countriesAndRadios[station["4"].toString()] == null){
                countriesAndRadios[station["4"].toString()] = mutableListOf(station)
            }
            else {
                (countriesAndRadios[station["4"].toString()] as MutableList).add(station)
            }
        }

        val result : MutableList<Country> = mutableListOf()
        for(country in countriesAndRadios){
            if(country.key.isEmpty() || country.key == "-"){
                logger.atWarn().log("PAÍS SIN NOMBRE: $country" )
            }
            else {
                val radioList: MutableList<Radio> = mutableListOf()
                for (radio in country.value) {
                    if (radio["1"].isNullOrEmpty() || radio["6"].isNullOrEmpty()) {
                        logger.atWarn().log("RADIO SIN NOMBRE: $radio")
                    } else {
                        radioList.add(
                            Radio(
                                name = radio["1"] as String,
                                description = radio["2"],
                                category = radio["3"],
                                language = radio["5"],
                                url = radio["6"] as String,
                                url2 = radio["7"],
                                url3 = radio["8"],
                                url4 = radio["9"],
                                url5 = radio["10"],
                                isFavourite = false
                            )
                        )
                    }
                }

                result.add(
                    Country(
                        id = country.key,
                        name = country.key,
                        radios = radioList
                    )
                )
            }
        }

        return result
    }
}