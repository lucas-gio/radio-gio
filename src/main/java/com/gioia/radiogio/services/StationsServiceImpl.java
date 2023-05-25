package com.gioia.radiogio.services;

import com.gioia.radiogio.data.domains.RadioStation;
import com.gioia.radiogio.data.repositories.RadioStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StationsServiceImpl implements StationsService{
    @Autowired
    public RadioStationRepository radioStationRepository;

    @Override
    public List<RadioStation> test(String countryCode) {
        return radioStationRepository.getTestRadioStations(countryCode);
    }
}
