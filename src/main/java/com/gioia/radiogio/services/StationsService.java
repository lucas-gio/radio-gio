package com.gioia.radiogio.services;

import com.gioia.radiogio.data.domains.RadioStation;

import java.util.List;

public interface StationsService {
    /**
     *    List 5 radio stations of an country
     */
    List<RadioStation> test(String countryCode);

    void saveAll(List<RadioStation> radioStationList);

    RadioStation getRadioStationByUrl(String url);
}
