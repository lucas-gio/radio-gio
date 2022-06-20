package com.gioia.radio.data.repositories

import com.gioia.radio.data.domains.Country
import org.dizitart.no2.*
import org.dizitart.no2.objects.ObjectFilter
import org.dizitart.no2.objects.filters.ObjectFilters
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CountryRepositoryImpl (
    private val database: Nitrite,
    private var logger: Logger = LoggerFactory.getLogger(CountryRepositoryImpl::class.java)
): CountryRepository {
    override fun removeAll(){
        database.getRepository(Country::class.java).remove(ObjectFilters.ALL)
    }

    override fun saveAll(countries:List<Country>){
        for(country in countries){
            database
                .getRepository(Country::class.java)
                .insert(country)
        }
    }

    override fun createIndexes(){
        val repository = database.getRepository(Country::class.java)
        if (repository != null && !repository.hasIndex("name")) {
            repository.createIndex("name",
                IndexOptions.indexOptions(IndexType.NonUnique, false)
            )
        }
    }

    override fun getInitialRadioStations(): List<Country>{
        return database
            .getRepository(Country::class.java)
            ?.find(
                FindOptions
                    .sort("name", SortOrder.Ascending)
                    .thenLimit(0, 5)
            )
            ?.toList() ?: emptyList()
    }

    override fun findByCountryNameLike(countryName: String): List<Country>{
        return findFiveCountryResults(
            ObjectFilters.regex("name", "^(?i).{0,}$countryName.{0,}$"))
    }

    override fun findByRadioNameLike(radioName: String): List<Country>{
        return findFiveCountryResults(
            ObjectFilters.elemMatch("radios",
                    ObjectFilters.regex("name", "^(?i).{0,}$radioName{0,}$")))
    }

    override fun setFavourite(countryName: String, radioName: String) {
        TODO("")//fixme
        /*database
            .getRepository(Country::class.java)
            .update(

            )*/
    }

    private fun findFiveCountryResults(criteria: ObjectFilter): List<Country>{
        val result: List<Country> = database
            .getRepository(Country::class.java)
            ?.find(
                criteria,
                FindOptions
                    .sort("name", SortOrder.Ascending)
                    .thenLimit(0, 5)
            )
            ?.toList() ?: emptyList()

        if(logger.isDebugEnabled){
            logger.atDebug().log("Resultados obtenidos de la búsqueda: ")
            logger.atDebug().log("**************************************************************************************************")
            for (country in result) {
                logger.atDebug().log("-------------------------------------------------------------------------------------------------")
                for(radio in country.radios!!){
                    logger.atDebug().log("${country.name.uppercase()} -- ${radio.name}")
                }
            }
        }

        return result
    }
}