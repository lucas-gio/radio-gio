package com.gioia.radiogio.data.repositories;

import com.gioia.radiogio.data.domains.RadioStation;

import java.util.List;

public interface RadioStationRepository extends Indexable{
    void removeAll();
    void saveAll(List<RadioStation> radioStation);
    List<RadioStation> getInitialRadioStations();
    List<RadioStation> getFavoritesRadioStations();
    List<RadioStation> findByCountryNameLike(String countryCode);
    List<RadioStation> findByCountryName(String countryCode);
    List<RadioStation> findByRadioNameLike(String radioName);
    void updateOne(RadioStation radioStation);
}