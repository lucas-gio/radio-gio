package com.gioia.radio.data.repositories

import com.gioia.radio.data.domains.RadioStation
import org.dizitart.no2.*
import org.dizitart.no2.exceptions.UniqueConstraintException
import org.dizitart.no2.objects.ObjectFilter
import org.dizitart.no2.objects.filters.ObjectFilters
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RadioStationRepositoryImpl (
    private val database: Nitrite,
    private var logger: Logger = LoggerFactory.getLogger(RadioStationRepositoryImpl::class.java)
): RadioStationRepository {
    override fun removeAll(){
        database.getRepository(RadioStation::class.java).remove(ObjectFilters.ALL)
        logger.atDebug().log("Removed all radio stations from database.")
    }

    override fun saveAll(radioStation: List<RadioStation>){
        val repository =  database.getRepository(RadioStation::class.java)
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
        val repository = database.getRepository(RadioStation::class.java)
        if (repository != null && !repository.hasIndex("name")) {
            repository.createIndex("name",
                IndexOptions.indexOptions(IndexType.NonUnique, false)
            )
        }
    }

    override fun getInitialRadioStations(): List<RadioStation>{
        return findByCountryNameLike("Portugal")
        /*return database
            .getRepository(Country::class.java)
            ?.find(
                FindOptions
                    .sort("name", SortOrder.Ascending)
                    .thenLimit(0, 5)
            )
            ?.toList() ?: emptyList()*/ //
    }

    override fun findByCountryNameLike(countryCode: String): List<RadioStation>{
        return findByCriteriaWithLimit(ObjectFilters.regex("countryCode", "^(?i).{0,}$countryCode.{0,}$"))
    }

    override fun findByRadioNameLike(radioName: String): List<RadioStation>{
        TODO("")
        //return findFiveCountryResults(
        //    ObjectFilters.elemMatch("radios",
        //            ObjectFilters.regex("name", "^(?i).{0,}$radioName{0,}$")))
    }

    private fun findByCriteriaWithLimit(criteria: ObjectFilter): List<RadioStation>{
        return database
            .getRepository(RadioStation::class.java)
            ?.find(
                criteria,
                FindOptions
                    .sort("name", SortOrder.Ascending)
                    .thenLimit(0, 5000)
            )
            ?.toList() ?: emptyList()
    }
}