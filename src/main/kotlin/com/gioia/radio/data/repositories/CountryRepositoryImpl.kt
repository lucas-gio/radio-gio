package com.gioia.radio.data.repositories

import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.Database
import org.dizitart.no2.IndexOptions
import org.dizitart.no2.IndexType
import org.dizitart.no2.objects.filters.ObjectFilters

class CountryRepositoryImpl : CountryRepository {
    override fun removeAll(){
        Database.getInstance()?.getRepository(Country::class.java)?.remove(ObjectFilters.ALL)
    }

    override fun saveAll(countries:List<Country>){
        for(country in countries){
            Database
                .getInstance()
                ?.getRepository(Country::class.java)
                ?.insert(country)
        }
    }

    override fun createIndexes(){
        val repository = Database.getInstance()?.getRepository(Country::class.java)
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