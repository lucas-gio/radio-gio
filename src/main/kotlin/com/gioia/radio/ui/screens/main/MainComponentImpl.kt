package com.gioia.radio.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.consume
import com.gioia.radio.config.dk
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.data.enums.ConfigKey
import com.gioia.radio.data.repositories.ConfigurationRepository
import com.gioia.radio.data.repositories.CountryRepository
import com.gioia.radio.services.PlayerService
import org.kodein.di.instance
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class MainComponentImpl(
    private val playerService: PlayerService,
    private val countryRepository: CountryRepository,
    private val configurationRepository: ConfigurationRepository,
    stateKeeper: StateKeeper,
) : MainComponent {
    private var logger: Logger = LoggerFactory.getLogger(MainComponentImpl::class.java)

    // Si statekeeper contiene el valor almacenado, entonces lo usa, sino usa un nuevo objeto Model.
    private val _model = mutableStateOf(stateKeeper.consume(key = this.javaClass.name) ?: MainModel())
    override val model: State<MainModel> = _model
    private val state by model // para evitar hacer model.value siempre

    override var componentContext: ComponentContext? = null
    override var onConfigPressed: () -> Unit = {}
    init {
        changeState { state.copy(
            countries = countryRepository.getInitialRadioStations(),
            volume = configurationRepository.find(ConfigKey.Volume.toString())?.value?.toFloatOrNull() ?: 50f,
        )}
        logger.atDebug().log("Valores iniciales estalecidos")
    }

     init {
         stateKeeper.register(this.javaClass.name) { state }
     }

    private inline fun changeState(reducer: MainModel.() -> MainModel): MainModel {
        val newModel = state.reducer()
        _model.value = newModel
        return newModel
    }

    @Composable
    override fun render() {
        Main(
            //componentContext = componentContext,
            messageService = dk.instance(),
            mainComponent = this
        )
    }

    override fun onRadioSelected(radio: Radio) {
        if(radio.name == state.selectedRadio?.name) return
        logger.atDebug().log("Seleccionada la radio ${radio.name}")
        onPlayPressed(true, radio)
    }

    override fun onSearchByCountryName(countryName: String) {
        changeState { state.copy(countryFilter = countryName, countries = countryRepository.findByCountryNameLike(countryName)) }
        logger.atDebug().log("Buscó por nombre de país, buscando por $countryName")
    }

    override fun onSearchByRadioName(text: String) {
        changeState { state.copy(radioFilter = text, countries = countryRepository.findByRadioNameLike(countryFilter)) }
        logger.atDebug().log("Buscó por nombre de radio, buscando por $text")
    }

    override fun onPlayPressed(isPlaying: Boolean?, radio: Radio?){
        changeState { state.copy(isPlaying = isPlaying ?: !state.isPlaying, selectedRadio = radio ?: state.selectedRadio)}
        playerService.playRadio(state.selectedRadio?.url ?: radio?.url ?: "")
        logger.atDebug().log("Reproduciendo la radio ${state.selectedRadio?.name}")
    }

    override fun onStopPressed(){
        changeState { state.copy(isPlaying = !state.isPlaying) }
        playerService.stopRadio()
        logger.atDebug().log("Apagando la radio ${state.selectedRadio?.name}")
    }

    override fun onFavouritePressed(){
        logger.atDebug().log("Añandiendo a / eliminando de favoritos la radio ${state.selectedRadio?.name}")
        playerService.toggleFavourite(state.isFavourite, state.selectedRadio)
        changeState { state.copy(isFavourite = !state.isFavourite) }
    }

    override fun onClearCountryFilter(){
        logger.atDebug().log("Limpiando filtro de país")
        changeState { state.copy(countryFilter = "") }
    }

    override fun onClearRadioFilter(){
        logger.atDebug().log("Limpiando filtro de radio")
        changeState { state.copy(radioFilter = ""/*, countries = countryRepository.findByCountryNameLike("")*/) }
    }

    override fun onPreviousPressed(){
        TODO("")
    }

    override fun onNextPressed(){
        TODO("")
    }

    override fun onVolumeChange(value: Float){
        logger.atDebug().log("Cambiando el volumen a $value")
        playerService.changeVolume(value.toInt())
        changeState { state.copy(volume = value) }
    }

    override fun onVolumeConfirmed(){
        val volumeConfig = configurationRepository.find(ConfigKey.Volume.toString())
        if(volumeConfig != null){
            volumeConfig.value = state.volume.toString()
            configurationRepository.upsert(volumeConfig)
        }
    }
}