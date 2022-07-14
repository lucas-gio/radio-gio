package com.gioia.radio.ui.screens.stations

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gioia.radio.data.domains.RadioStation

@Parcelize
data class StationsModel(
    var radioStations: List<RadioStation> = emptyList(),
    val countryFilter: String = "",
    val radioFilter: String = "",
    val selectedCountryName: String = "",
    val selectedRadioStation: RadioStation? = null,
    var isPlaying: Boolean = false,
    val isFavourite: Boolean = false,
    var resume: String = "",
    var volume: Float = 50f,
    var isExpanded: Boolean = false
): Parcelable {
    init{
        if(selectedRadioStation != null){
            resume = "${selectedRadioStation.name} - ${selectedRadioStation.description}"
        }
    }
}