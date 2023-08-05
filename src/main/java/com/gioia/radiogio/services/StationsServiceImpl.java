package com.gioia.radiogio.services;

import com.gioia.radiogio.data.domains.RadioStation;
import com.gioia.radiogio.data.repositories.RadioStationRepository;
import com.gioia.radiogio.helpers.EncoderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StationsServiceImpl implements StationsService{
    @Autowired
    public RadioStationRepository radioStationRepository;

    @Override
    public List<RadioStation> test(String countryCode) {
        return radioStationRepository.findAll(
                Example.of(
                        RadioStation.builder()
                                .countryCode(countryCode)
                                .build()
                )
        )
        .stream()
        .map((RadioStation radioStation) -> {
                    radioStation.setUrl(EncoderHelper.encode(radioStation.getUrl()));
                    return radioStation;
                })
        .collect(Collectors.toList());
    }

    public void saveAll(List<RadioStation> radioStationList){
        radioStationRepository.saveAllAndFlush(radioStationList);
    }
}
