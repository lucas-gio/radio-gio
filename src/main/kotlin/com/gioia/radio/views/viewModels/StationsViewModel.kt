package com.gioia.radio.views.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.data.enums.ConfigKey
import com.gioia.radio.data.repositories.ConfigurationRepository
import com.gioia.radio.data.repositories.CountryRepository
import com.gioia.radio.services.PlayerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class StationsViewModel(
    private val playerService: PlayerService,
    private val countryRepository: CountryRepository,
    private val configurationRepository: ConfigurationRepository,
)  {
    //  private val stateKeeper: StateKeeper by di.instance()
    private var logger: Logger = LoggerFactory.getLogger(StationsViewModel::class.java)
    //private val boxStationsState: String = "boxStationsState"

    // Si statekeeper contiene el valor almacenado, entonces lo usa, sino usa un nuevo objeto Model.
    private val _model = mutableStateOf(/*stateKeeper.consume(boxStationsState) ?: */StationsModel())
    val model: State<StationsModel> = _model
    private val state by model // para evitar hacer model.value todo el tiempo

    init {
        changeState { state.copy(
            countries = countryRepository.getInitialRadioStations(),
            volume = configurationRepository.find(ConfigKey.Volume.toString())?.value?.toFloatOrNull() ?: 50f,
        )}
        logger.atDebug().log("Valores iniciales estalecidos")
        configurationRepository.find(ConfigKey.Locale.toString())
    }

    /* @Parcelize*/ // Preserva el estado cuando se rota una pantalla android.
    data class StationsModel(
        var countries: List<Country> = emptyList(),
        val countryFilter: String = "",
        val radioFilter: String = "",
        val selectedCountryName: String = "",
        val selectedRadio: Radio? = null,
        var isPlaying: Boolean = false,
        val isFavourite: Boolean = false,
        var resume: String = "",
        var volume: Float = 50f,
        var isExpanded: Boolean = false,
    )/*: Parcelable */{
        init{
            if(selectedRadio != null){
                resume = "${selectedRadio.name} - ${selectedRadio.description}"
            }
        }
    }

    /* init {
         stateKeeper.register(boxStationsState, listState::value)
     }*/

    private inline fun changeState(reducer: StationsModel.() -> StationsModel): StationsModel {
        val newModel = state.reducer()
        _model.value = newModel
        return newModel
    }

    fun onRadioSelected(radio: Radio) {
        if(radio.name == state.selectedRadio?.name) return
        logger.atDebug().log("Seleccionada la radio ${radio.name}")
        onPlayPressed(true, radio)
    }

    fun onSearchByCountryName(countryName: String) {
        changeState { state.copy(countryFilter = countryName, countries = countryRepository.findByCountryNameLike(countryName)) }
        logger.atDebug().log("Buscó por nombre de país, buscando por $countryName")
    }

    fun onSearchByRadioName(text: String) {
        changeState { state.copy(radioFilter = text, countries = countryRepository.findByRadioNameLike(countryFilter)) }
        logger.atDebug().log("Buscó por nombre de radio, buscando por $text")
    }

    fun onPlayPressed(isPlaying: Boolean? = null, radio: Radio? = null){
        changeState { state.copy(isPlaying = isPlaying ?: !state.isPlaying, selectedRadio = radio ?: state.selectedRadio)}
        playerService.playRadio(state.selectedRadio?.url ?: radio?.url ?: "")
        logger.atDebug().log("Reproduciendo la radio ${state.selectedRadio?.name}")
    }

    fun onStopPressed(){
        changeState { state.copy(isPlaying = !state.isPlaying) }
        playerService.stopRadio()
        logger.atDebug().log("Apagando la radio ${state.selectedRadio?.name}")
    }

    fun onFavouritePressed(){
        logger.atDebug().log("Añandiendo a / eliminando de favoritos la radio ${state.selectedRadio?.name}")
        playerService.toggleFavourite(state.isFavourite, state.selectedRadio)
        changeState { state.copy(isFavourite = !state.isFavourite) }
    }

    fun onClearCountryFilter(){
        logger.atDebug().log("Limpiando filtro de país")
        changeState { state.copy(countryFilter = "") }
    }

    fun onClearRadioFilter(){
        logger.atDebug().log("Limpiando filtro de radio")
        changeState { state.copy(radioFilter = ""/*, countries = countryRepository.findByCountryNameLike("")*/) }
    }

    /**
     * Todo: implementar
     */
    fun onPreviousPressed(){

    }

    /**
     * Todo: implementar
     */
    fun onNextPressed(){

    }

    fun onVolumeChange(value: Float){
        logger.atDebug().log("Cambiando el volumen a $value")
        playerService.changeVolume(value.toInt())
        changeState { state.copy(volume = value) }
    }

    fun onVolumeConfirmed(){
        val volumeConfig = configurationRepository.find(ConfigKey.Volume.toString())
        if(volumeConfig != null){
            volumeConfig.value = state.volume.toString()
            configurationRepository.upsert(volumeConfig)
        }
    }
}