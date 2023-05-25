package com.gioia.radiogio.data.repositories;

import com.gioia.radiogio.data.domains.Country;
import org.dizitart.no2.*;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class CountryRepositoryImpl implements CountryRepository{
    private Nitrite database;
    private Logger logger = LoggerFactory.getLogger(CountryRepositoryImpl.class);

    private ObjectRepository<Country> getRepository() {
        return database.getRepository(Country.class);
    }

    @Override
     public void removeAll() {
        getRepository().remove(ObjectFilters.ALL);
        logger.atDebug().log("Removed all countries from database.");
    }

    @Override
    public void saveAll(List<Country> countries) {
        ObjectRepository repository = getRepository();
        countries.stream().forEach(country-> {
            try {
                repository.insert(country);
                logger.atDebug().log("Inserted country: ${it.code}");
            }
            catch (UniqueConstraintException e) {
                logger.atDebug().log("REPEATED IGNORED COUNTRY: ${it.code}");
            }
        });
    }

    @Override
    public List<Country> findAllCountries(){
        return getRepository()
        .find(
            FindOptions.sort("code", SortOrder.Ascending)
        )
        .toList();
    }

    @Override
     public void createIndexes() {
        ObjectRepository repository = getRepository();
        if (!repository.hasIndex("code")) {
            repository.createIndex(
                "code",
                IndexOptions.indexOptions(IndexType.NonUnique, false)
            );
        }
    }


    @Override
     public void setFavourite(String countryName, String radioName) {
        //TODO("")//
        /*database
            .getRepository(Country::class.java)
            .update(

            )*/
    }
}