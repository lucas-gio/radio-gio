package com.gioia.radio.ui.screens.main

import androidx.compose.runtime.State
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.ui.navigation.Component

interface MainComponent: Component {
    val model: State<MainModel>
    var componentContext: ComponentContext?
    var onConfigPressed: () -> Unit

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