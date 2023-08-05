package com.gioia.radiogio.services;

import com.gioia.radiogio.data.domains.Country;
import com.gioia.radiogio.data.domains.RadioStation;
import com.gioia.radiogio.data.repositories.CountryRepository;
import com.gioia.radiogio.data.repositories.RadioStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CountryServiceImpl implements CountryService{
    @Autowired
    public CountryRepository countryRepository;

    public void saveAll(List<Country> countries){
        countryRepository.saveAllAndFlush(countries);
    }
}
