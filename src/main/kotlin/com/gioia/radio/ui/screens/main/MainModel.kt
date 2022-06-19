package com.gioia.radio.ui.screens.main

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.Radio

@Parcelize
data class MainModel(
    var countries: List<Country> = emptyList(),
    val countryFilter: String = "",
    val radioFilter: String = "",
    val selectedCountryName: String = "",
    val selectedRadio: Radio? = null,
    var isPlaying: Boolean = false,
    val isFavourite: Boolean = false,
    var resume: String = "",
    var volume: Float = 50f,
    var isExpanded: Boolean = false
): Parcelable {
    init{
        if(selectedRadio != null){
            resume = "${selectedRadio.name} - ${selectedRadio.description}"
        }
    }
}