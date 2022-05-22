package com.gioia.radio.services

interface PlayerService {
    fun playRadio(url: String)
    fun stopRadio()
}