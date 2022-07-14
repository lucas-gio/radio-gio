package com.gioia.radio.services

import com.gioia.radio.data.domains.RadioStation

interface PlayerService {
    fun playRadio(url: String)
    fun stopRadio()
    fun toggleFavourite(isNowInFavourite: Boolean, radioStation: RadioStation?)
    fun changeVolume(value: Int)
}