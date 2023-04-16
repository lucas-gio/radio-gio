package com.gioia.radio.data.repositories

import com.gioia.radio.data.domains.RadioStation
import com.gioia.radio.ui.themes.Globals
import org.dizitart.no2.*
import org.dizitart.no2.exceptions.UniqueConstraintException
import org.dizitart.no2.objects.ObjectFilter
import org.dizitart.no2.objects.ObjectRepository
import org.dizitart.no2.objects.filters.ObjectFilters
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RadioStationRepositoryImpl (
    private val database: Nitrite,
    private var logger: Logger = LoggerFactory.getLogger(RadioStationRepositoryImpl::class.java)
): RadioStationRepository {

    private fun getRepository(): ObjectRepository<RadioStation> {
        return database.getRepository(RadioStation::class.java)
    }
    override fun removeAll(){
        getRepository().remove(ObjectFilters.ALL)
        logger.atDebug().log("Removed all radio stations from database.")
    }

    override fun saveAll(radioStation: List<RadioStation>){
        val repository =  getRepository()
        radioStation.forEach {
                try {
                    repository.insert(it)
                    logger.atDebug().log("Inserted radio station: ${it.name} - ${it.countryCode}")
                }
                catch (e: UniqueConstraintException) {
                    logger.atDebug().log("REPEATED IGNORED STATION: ${it.name}")
                }
            }
    }

    override fun createIndexes(){
        val repository = getRepository()

        listOf("name", "isFavorite")
            .forEach { newIndex->
                if (!repository.hasIndex(newIndex)) {
                    repository.createIndex(newIndex, IndexOptions.indexOptions(IndexType.NonUnique, false))
                }
            }
    }

    override fun getInitialRadioStations(): List<RadioStation>{
        return findByCountryNameLike(Globals.selectedCountryCode)
        /*return getRepository()
            ?.find(
                FindOptions
                    .sort("name", SortOrder.Ascending)
                    .thenLimit(0, 5)
            )
            ?.toList() ?: emptyList()*/ //
    }

    override fun getFavoritesRadioStations(): List<RadioStation>{
        return getRepository()
            .find(
                ObjectFilters.eq("isFavourite", true),
                FindOptions.sort("name", SortOrder.Ascending)
            )
            ?.toList() ?: emptyList()
    }

    override fun findByCountryNameLike(countryCode: String): List<RadioStation>{
        return findByCriteriaWithLimit(ObjectFilters.regex("countryCode", "^(?i).{0,}$countryCode.{0,}$"))
    }
    override fun findByCountryName(countryCode: String): List<RadioStation>{
        return findByCriteriaWithLimit(ObjectFilters.eq("countryCode", countryCode))
    }


    override fun findByRadioNameLike(radioName: String): List<RadioStation>{
        TODO("")
        //return findFiveCountryResults(
        //    ObjectFilters.elemMatch("radios",
        //            ObjectFilters.regex("name", "^(?i).{0,}$radioName{0,}$")))
    }

    private fun findByCriteriaWithLimit(criteria: ObjectFilter): List<RadioStation>{
        return getRepository()
            .find(
                criteria,
                FindOptions
                    .sort("name", SortOrder.Ascending)
                    .thenLimit(0, 5000)
            )
            ?.toList() ?: emptyList()
    }

    override fun updateOne(radioStation: RadioStation){
        getRepository().update(radioStation)
    }
}