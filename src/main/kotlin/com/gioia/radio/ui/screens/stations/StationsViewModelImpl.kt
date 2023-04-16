package com.gioia.radio.ui.screens.stations

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.consume
import com.gioia.radio.data.domains.RadioStation
import com.gioia.radio.data.enums.ConfigKey
import com.gioia.radio.data.repositories.ConfigurationRepository
import com.gioia.radio.data.repositories.RadioStationRepository
import com.gioia.radio.services.PlayerService
import com.gioia.radio.ui.util.ViewModel
import kotlinx.coroutines.CoroutineScope
import org.slf4j.Logger
import org.slf4j.LoggerFactory


open class StationsViewModelImpl(
    private val playerService: PlayerService,
    val radioStationsRepository: RadioStationRepository,
    private val configurationRepository: ConfigurationRepository,
    stateKeeper: StateKeeper,
) : StationsViewModel, ViewModel() {
    private var logger: Logger = LoggerFactory.getLogger(StationsViewModelImpl::class.java)

    // Si statekeeper contiene el valor almacenado, entonces lo usa, sino usa un nuevo objeto Model.
    protected val _model = mutableStateOf(stateKeeper.consume(key = this.javaClass.name) ?: StationsModel())
    override val model: State<StationsModel> = _model
    protected val state by model // para evitar hacer model.value siempre

    override var componentContext: ComponentContext? = null
    init {
        changeState { state.copy(
            radioStations = radioStationsRepository.getInitialRadioStations(),
            volume = configurationRepository.find(ConfigKey.Volume.toString())?.value?.toFloatOrNull() ?: 50f,
        )}
        logger.atDebug().log("Valores iniciales estalecidos")

        stateKeeper.register(this.javaClass.name) { state }
    }

    override fun init(viewModelScope: CoroutineScope) {
        super.init(viewModelScope)

        /*viewModelScope.launch {
            delay(WelcomeViewModelImpl.SPLASH_DELAY)
            _isWelcomeFinished.value = true
        }*/
    }

    protected inline fun changeState(reducer: StationsModel.() -> StationsModel): StationsModel {
        val newModel = state.reducer()
        _model.value = newModel
        return newModel
    }



    override fun onRadioSelected(radioStation: RadioStation) {
        if(radioStation.name == state.selectedRadioStation?.name) return
        logger.atDebug().log("Seleccionada la radio ${radioStation.name}")
        onPlayPressed(true, radioStation)
    }

    override fun onSearchByCountryName(countryName: String) {
        changeState { state.copy(countryFilter = countryName, radioStations = radioStationsRepository.findByCountryNameLike(countryName)) }
        logger.atDebug().log("Buscó por nombre de país, buscando por $countryName")
    }

    override fun onSearchByRadioName(text: String) {
        changeState { state.copy(radioFilter = text, radioStations = radioStationsRepository.findByRadioNameLike(countryFilter)) }
        logger.atDebug().log("Buscó por nombre de radio, buscando por $text")
    }

    override fun onPlayPressed(isPlaying: Boolean?, radioStation: RadioStation?){
        changeState {
            state.copy(
                isPlaying = isPlaying ?: !state.isPlaying,
                selectedRadioStation = radioStation ?: state.selectedRadioStation,
                isFavourite = radioStation?.isFavourite ?: state.selectedRadioStation?.isFavourite ?: false
            )
        }
        playerService.playRadio(state.selectedRadioStation?.url ?: radioStation?.url ?: "")
        logger.atDebug().log("Reproduciendo la radio ${state.selectedRadioStation?.name}")
    }

    override fun onStopPressed(){
        changeState { state.copy(isPlaying = !state.isPlaying) }
        playerService.stopRadio()
        logger.atDebug().log("Apagando la radio ${state.selectedRadioStation?.name}")
    }

    override fun onFavouritePressed(){
        changeState { state.copy(isFavourite = !state.isFavourite) }
        logger.atDebug().log("Añandiendo a / eliminando de favoritos la radio ${state.selectedRadioStation?.name}")
        playerService.toggleFavourite(state.isFavourite, state.selectedRadioStation)
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

    override fun onCountrySelected(countryCode: String){
        changeState { state.copy(countryFilter = "", radioStations = radioStationsRepository.findByCountryName(countryCode)) }
        logger.atDebug().log("Se cambió estado radioStations a radios de $countryCode")
        if(state.isPlaying) {
            onStopPressed()
        }
    }
}