package com.gioia.radiogio.services;

import com.gioia.radiogio.data.domains.RadioStation;
import com.gioia.radiogio.data.repositories.RadioStationRepository;
import com.gioia.radiogio.helpers.EncoderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StationsServiceImpl implements StationsService{
    @Autowired
    public RadioStationRepository radioStationRepository;

    @Override
    public List<RadioStation> test(String countryCode) {
        return radioStationRepository.findAll(
               /* Example.of(
                        RadioStation.builder()
                                .countryCode(countryCode)
                                .build()
                )*/
        )
        .stream()
                .limit(100)

        .map((RadioStation it) -> {
            it.setUrl(EncoderHelper.encode(it.getUrl()));
                    return it;
                })
        .collect(Collectors.toList());
    }

    @Override
    public RadioStation getRadioStationByUrl(String url) {
        return radioStationRepository.findAll(
                Example.of(
                        RadioStation.builder()
                                .url(url)
                                .build()
                )
                )
                .stream()
                .limit(1)
                .toList()
                .get(0);
    }

    public void saveAll(List<RadioStation> radioStationList){
        radioStationRepository.saveAllAndFlush(radioStationList);
    }
}
