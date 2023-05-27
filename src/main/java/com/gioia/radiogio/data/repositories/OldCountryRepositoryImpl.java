package com.gioia.radiogio.data.repositories;

import com.gioia.radiogio.data.domains.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OldCountryRepositoryImpl implements OldCountryRepository {
    private Logger logger = LoggerFactory.getLogger(OldCountryRepositoryImpl.class);

    @Override
     public void removeAll() {
        //getRepository().remove(ObjectFilters.ALL);
        logger.atDebug().log("Removed all countries from database.");
    }

    @Override
    public void saveAll(List<Country> countries) {
        /*ObjectRepository repository = getRepository();
        countries.stream().forEach(country-> {
            try {
                repository.insert(country);
                logger.atDebug().log("Inserted country: ${it.code}");
            }
            catch (UniqueConstraintException e) {
                logger.atDebug().log("REPEATED IGNORED COUNTRY: ${it.code}");
            }
        });*/
    }

    @Override
    public List<Country> findAllCountries(){
        /*return getRepository()
        .find(
            FindOptions.sort("code", SortOrder.Ascending)
        )
        .toList();*/
        return List.of();
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