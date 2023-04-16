package com.gioia.radio.ui.screens.stations

import androidx.compose.runtime.State
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.data.domains.RadioStation

interface StationsViewModel{
    val model: State<StationsModel>
    var componentContext: ComponentContext?

    fun onRadioSelected(radioStation: RadioStation)
    fun onCountrySelected(countryCode: String)
    fun onSearchByCountryName(countryName: String)
    fun onSearchByRadioName(text: String)
    fun onPlayPressed(isPlaying: Boolean? = null, radioStation: RadioStation? = null)
    fun onStopPressed()
    fun onFavouritePressed()
    fun onClearCountryFilter()
    fun onClearRadioFilter()
    fun onPreviousPressed()
    fun onNextPressed()
    fun onVolumeChange(value: Float)
    fun onVolumeConfirmed()
}