package com.gioia.radiogio.data.repositories;

import com.gioia.radiogio.data.domains.RadioStation;
import org.dizitart.no2.*;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class RadioStationRepositoryImpl implements RadioStationRepository {
    private Nitrite database;
    private Logger logger = LoggerFactory.getLogger(RadioStationRepositoryImpl.class);

    private ObjectRepository<RadioStation> getRepository() {
        return database.getRepository(RadioStation.class);
    }


    @Override
    public void removeAll(){
        getRepository().remove(ObjectFilters.ALL);
        logger.atDebug().log("Removed all radio stations from database.");
    }

    @Override
    public void saveAll(List<RadioStation> radioStation){
        ObjectRepository repository =  getRepository();
        radioStation
            .stream()
            .forEach(it-> {
                try {
                    repository.insert(it);
                    logger.atDebug().log("Inserted radio station: ${it.name} - ${it.countryCode}");
                }
                catch (UniqueConstraintException e) {
                    logger.atDebug().log("REPEATED IGNORED STATION: ${it.name}");
                }
            });
    }

    @Override
    public void createIndexes(){
        ObjectRepository repository = getRepository();

        Stream.of("name", "isFavorite")
            .filter(repository::hasIndex)
            .forEach(newIndex->
                repository.createIndex(newIndex, IndexOptions.indexOptions(IndexType.NonUnique, false))
            );
    }

    @Override
    public List<RadioStation> getInitialRadioStations(){
        return findByCountryNameLike("AR");
        /*return getRepository()
            ?.find(
                FindOptions
                    .sort("name", SortOrder.Ascending)
                    .thenLimit(0, 5)
            )
            ?.toList() ?: emptyList()*/ //
    }

    @Override
    public List<RadioStation> getFavoritesRadioStations(){
        return getRepository()
            .find(
                ObjectFilters.eq("isFavourite", true),
                FindOptions.sort("name", SortOrder.Ascending)
            )
            .toList();
    }


    @Override
    public List<RadioStation> findByCountryNameLike(String countryCode){
        return findByCriteriaWithLimit(ObjectFilters.regex("countryCode", "^(?i).{0,}$countryCode.{0,}$"));
    }

    public List<RadioStation> findByCountryName(String countryCode){
        return findByCriteriaWithLimit(ObjectFilters.eq("countryCode", countryCode));
    }

    @Override
     public List<RadioStation> findByRadioNameLike(String radioName){
        //TODO("")
        //return findFiveCountryResults(
        //    ObjectFilters.elemMatch("radios",
        //            ObjectFilters.regex("name", "^(?i).{0,}$radioName{0,}$")))
        return List.of();
    }

    private List<RadioStation> findByCriteriaWithLimit(ObjectFilter criteria){
        return getRepository()
            .find(
                criteria,
                FindOptions
                    .sort("name", SortOrder.Ascending)
                    .thenLimit(0, 5000)
            )
            .toList();
    }

    @Override
    public void updateOne(RadioStation radioStation){
        getRepository().update(radioStation);
    }
}