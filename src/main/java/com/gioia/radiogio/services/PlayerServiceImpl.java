package com.gioia.radiogio.services;

import com.gioia.radiogio.data.domains.RadioStation;
import com.gioia.radiogio.data.repositories.OldRadioStationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerServiceImpl implements PlayerService {
    //@Autowired
    private AudioPlayerComponent audioPlayerComponent;

    @Autowired
    private OldRadioStationRepository oldRadioStationRepository;

    private Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Override
    public void playRadio(String url){
        audioPlayerComponent.mediaPlayer().media().play(url);
    }

    @Override
    public void stopRadio(){
        audioPlayerComponent.mediaPlayer().controls().pause();
    }

    @Override
    public void toggleFavourite(Boolean isNowInFavourite, RadioStation radioStation) {
        if(radioStation == null) return;

        if(isNowInFavourite){
            logger.atDebug().log("Añandiendo a favoritos la radio" + radioStation.getName());
        }
        else {
            logger.atDebug().log("Eliminando de favoritos la radio" + radioStation.getName());
        }

        radioStation.setIsFavourite(!radioStation.getIsFavourite());
        oldRadioStationRepository.updateOne(radioStation);
    }

    @Override
    public void changeVolume(Integer value){
        audioPlayerComponent.mediaPlayer().audio().setVolume(value);
    }
}