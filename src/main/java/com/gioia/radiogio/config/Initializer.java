package com.gioia.radiogio.config;

import com.gioia.radiogio.data.domains.Country;
import com.gioia.radiogio.data.domains.RadioStation;
import com.gioia.radiogio.services.CountryService;
import com.gioia.radiogio.services.StationsService;
import de.sfuhrm.radiobrowser4j.FieldName;
import de.sfuhrm.radiobrowser4j.ListParameter;
import de.sfuhrm.radiobrowser4j.RadioBrowser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Initializer {
    @Autowired
    private StationsService stationsService;

    @Autowired
    private CountryService countryService;

    private Logger logger = LoggerFactory.getLogger(Initializer.class);

    public void init(){
        logger.atDebug().log("Turning on.");
        logger.atDebug().log("Performing tasks before turning on Radio Gio. Starting");

        logger.atDebug().log("Database creation: STARTING");
        loadDatabase();
        logger.atDebug().log("Database creation: END");

        logger.atDebug().log("Performing tasks before turning on Radio Gio. End");
    }

    private void loadDatabase(){
        // 5000ms timeout, user agent is Demo agent/1.0
        List<RadioStation> radioStations = new RadioBrowser(5000, "Demo agent/1.0")
                .listStations(ListParameter.create().order(FieldName.NAME))
                .limit(50)
                .filter(station ->
                        ! station.getName().isBlank()
                )
                .peek(station ->
                        logger.atDebug().log("Converter: add " + station.getName() + " station")
                )
                .map(station ->
                        RadioStation.builder()
                                .name(station.getName())
                                .url(station.getUrlResolved())
                                .countryCode(station.getCountryCode())
                                .language(station.getLanguage())
                                .build()
                )
                .collect(Collectors.toList());

        List<Country> countryList = radioStations.stream()
                .map(radioStation ->
                        radioStation.getCountryCode()
                )
                .distinct()
                .map(countryCode ->
                        Country.builder()
                                .code(countryCode)
                                .build()
                )
                .collect(Collectors.toList());

        countryService.saveAll(countryList);
        countryList = null;

        stationsService.saveAll(radioStations);
        radioStations = null;
    }
}
