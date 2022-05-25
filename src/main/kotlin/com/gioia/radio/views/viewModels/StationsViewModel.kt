package com.gioia.radio.views.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.bonsai.core.node.BranchNode
import cafe.adriel.bonsai.core.node.LeafNode
import cafe.adriel.bonsai.core.node.Node
import com.gioia.radio.config.di
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.data.repositories.CountryRepository
import com.gioia.radio.services.PlayerService
import org.kodein.di.instance
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class StationsViewModel(
    private val playerService: PlayerService,
    private val countryRepository: CountryRepository
)  {

    //  private val stateKeeper: StateKeeper by di.instance()
    private var logger: Logger = LoggerFactory.getLogger(StationsViewModel::class.java)
    //private val boxStationsState: String = "boxStationsState"

    // Si statekeeper contiene el valor almacenado, entonces lo usa, sino usa un nuevo objeto Model.
    private val _model = mutableStateOf(/*stateKeeper.consume(boxStationsState) ?: */StationsModel())
    val model: State<StationsModel> = _model
    private val state by model // para evitar hacer model.value todo el tiempo

    /* @Parcelize*/ // Preserva el estado cuando se rota una pantalla android.
    data class StationsModel(
        var countries: List<Country> = emptyList(),
        val countryFilter: String = "",
        val radioFilter: String = "",
        val selectedCountryName: String = "",
        val selectedRadio: Radio? = null,
        var isPlaying: Boolean = false, //fixme: En un futuro podría ser configurable
        val isFavourite: Boolean = false
    )/*: Parcelable */{
        init{
            if(countries.isEmpty()) {
                val countryRepository: CountryRepository by di.instance()
                countries = countryRepository.getInitialRadioStations()
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

    private fun toggleExpansionBranch(node: BranchNode<Country>){
        node.setExpanded(!node.isExpanded, 1 )
        println("${if(!node.isExpanded) "Expandido" else "Colapsado"} el nodo ${node.name}")
    }

    private fun onRadioSelected(radio: Radio) {
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

    fun onDoubleclickInNode(node: Node<Country>){
        when (node) {
            is LeafNode<*> -> onRadioSelected(node.content as Radio)
            is BranchNode<Country> -> toggleExpansionBranch(node)
        }
    }

    fun onPlayPressed(isPlaying: Boolean? = null, radio: Radio? = null){
        changeState { state.copy(isPlaying = isPlaying ?: !state.isPlaying, selectedRadio = radio ?: state.selectedRadio)}
        playerService.playRadio(state.selectedRadio?.url ?: radio?.url ?: "") // fixme: hay varias url, debe considerarlas a todas si una falla. Además cada una de ellas da diferente calidad.
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

    fun onPreviousPressed(){

    }

    fun onNextPressed(){

    }
}