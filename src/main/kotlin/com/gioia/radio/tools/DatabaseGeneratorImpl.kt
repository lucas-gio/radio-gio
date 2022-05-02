package com.gioia.radio.tools

import com.gioia.radio.config.di
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.data.repositories.CountryRepository
import com.gioia.radio.data.repositories.CountryRepositoryImpl
import com.google.gson.Gson
import org.kodein.di.instance
import java.io.InputStreamReader
import java.io.Reader

class DatabaseGeneratorImpl (
    private val countryRepository: CountryRepository
) : DatabaseGenerator{
    override fun generateDatabase() {
        countryRepository.removeAll()
        countryRepository.saveAll(parseStationsJsonToList())
        countryRepository.createIndexes()
    }

    //fixme: Enrealidad hay que hacerlo por lotes, no llevar todo a memoria.
    // Ver como usar mejor una estructura para leerlo en vez de serializar y deserializar para llevarlo a la bd.
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

        //fixme: cambiar a un map() collect()
        val result : MutableList<Country> = mutableListOf()
        for(country in countriesAndRadios){
            val radioList: MutableList<Radio> = mutableListOf()
            for(radio in country.value){
                radioList.add(
                    Radio(
                        name = radio["1"] ,
                        description = radio["2"],
                        category = radio["3"],
                        language = radio["5"],
                        url = radio["6"],
                        url2 = radio["7"],
                        url3 = radio["8"],
                        url4 = radio["9"],
                        url5 = radio["10"])
                )
            }

            result.add(
                Country(
                    id=country.key,
                    name=country.key,
                    radios = radioList
                )
            )
        }

        return result
    }
}