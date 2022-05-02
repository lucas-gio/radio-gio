package com.gioia.radio.data.repositories

import com.gioia.radio.data.domains.Country
import org.dizitart.no2.IndexOptions
import org.dizitart.no2.IndexType
import org.dizitart.no2.Nitrite
import org.dizitart.no2.objects.filters.ObjectFilters

class CountryRepositoryImpl (
    private val database: Nitrite
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
            /*repository.createIndex("radios.name",
                IndexOptions.indexOptions(IndexType.NonUnique, false)
            )*///fixme:No funciona el nombre
        }
    }
}