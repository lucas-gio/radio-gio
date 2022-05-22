package com.gioia.radio.services

import uk.co.caprica.vlcj.player.component.AudioPlayerComponent

class PlayerServiceImpl(
    private var audioPlayerComponent: AudioPlayerComponent
) : PlayerService {
    override fun playRadio(url: String){
        audioPlayerComponent.mediaPlayer().media().play(url)
    }

    override fun stopRadio(){
        audioPlayerComponent.mediaPlayer().controls().pause()
    }
}