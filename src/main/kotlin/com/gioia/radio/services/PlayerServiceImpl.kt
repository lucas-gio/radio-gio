package com.gioia.radio.services

import com.gioia.radio.data.domains.RadioStation
import com.gioia.radio.data.repositories.RadioStationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent

class PlayerServiceImpl(
    private var audioPlayerComponent: AudioPlayerComponent,
    private var radioStationRepository: RadioStationRepository
) : PlayerService {
    private var logger: Logger = LoggerFactory.getLogger(PlayerServiceImpl::class.java)

    override fun playRadio(url: String){
        audioPlayerComponent.mediaPlayer().media().play(url)
    }

    override fun stopRadio(){
        audioPlayerComponent.mediaPlayer().controls().pause()
    }

    override fun toggleFavourite(isNowInFavourite: Boolean, radioStation: RadioStation?) {
        if(radioStation == null) return
        logger.atDebug().log("${if(isNowInFavourite) "Añandiendo a" else "Eliminando de"} favoritos la radio ${radioStation.name}")
        radioStation.isFavourite = !radioStation.isFavourite
        radioStationRepository.updateOne(radioStation)
    }

    override fun changeVolume(value: Int){
        audioPlayerComponent.mediaPlayer().audio().setVolume(value)
    }
}