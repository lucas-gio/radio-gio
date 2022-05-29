package com.gioia.radio.services

import com.gioia.radio.data.domains.Radio

interface PlayerService {
    fun playRadio(url: String)
    fun stopRadio()
    fun toggleFavourite(isNowInFavourite: Boolean, radio: Radio?)
    fun changeVolume(value: Int)
}