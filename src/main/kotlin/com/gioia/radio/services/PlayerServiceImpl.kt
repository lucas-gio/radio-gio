package com.gioia.radio.services

import com.gioia.radio.data.domains.Radio
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent

class PlayerServiceImpl(
    private var audioPlayerComponent: AudioPlayerComponent
) : PlayerService {
    private var logger: Logger = LoggerFactory.getLogger(PlayerServiceImpl::class.java)

    override fun playRadio(url: String){
        audioPlayerComponent.mediaPlayer().media().play(url)
    }

    override fun stopRadio(){
        audioPlayerComponent.mediaPlayer().controls().pause()
    }

    override fun toggleFavourite(isNowInFavourite: Boolean, radio: Radio?) {
        if(radio == null) return
        logger.atDebug().log("${if(isNowInFavourite) "Añandiendo a" else "Eliminando de"} favoritos la radio ${radio.name}")
    }
}