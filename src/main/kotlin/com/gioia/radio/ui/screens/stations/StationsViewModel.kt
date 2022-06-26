package com.gioia.radio.ui.screens.stations

import androidx.compose.runtime.State
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.data.domains.Radio

interface StationsViewModel{
    val model: State<MainModel>
    var componentContext: ComponentContext?

    fun onRadioSelected(radio: Radio)
    fun onSearchByCountryName(countryName: String)
    fun onSearchByRadioName(text: String)
    fun onPlayPressed(isPlaying: Boolean? = null, radio: Radio? = null)
    fun onStopPressed()
    fun onFavouritePressed()
    fun onClearCountryFilter()
    fun onClearRadioFilter()
    fun onPreviousPressed()
    fun onNextPressed()
    fun onVolumeChange(value: Float)
    fun onVolumeConfirmed()
}