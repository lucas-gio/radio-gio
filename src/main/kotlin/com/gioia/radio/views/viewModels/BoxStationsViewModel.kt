package com.gioia.radio.views.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.bonsai.core.node.BranchNode
import cafe.adriel.bonsai.core.node.LeafNode
import cafe.adriel.bonsai.core.node.Node
import com.gioia.radio.config.di
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.data.repositories.CountryRepository
import org.kodein.di.instance
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class BoxStationsViewModel  {
    private val countryRepository: CountryRepository by di.instance()
    private val boxPlayerViewModel: BoxPlayerViewModel by di.instance()

    //  private val stateKeeper: StateKeeper by di.instance()
    private var logger: Logger = LoggerFactory.getLogger(BoxPlayerViewModel::class.java)
    private val boxStationsState: String = "boxStationsState"

    // Si statekeeper contiene el valor almacenado, entonces lo usa, sino usa un nuevo objeto Model.
    private val _model = mutableStateOf(/*stateKeeper.consume(boxStationsState) ?: */StationsModel())
    val model: State<StationsModel> = _model

    /* @Parcelize*/ // Preserva el estado cuando se rota una pantalla android.
    data class StationsModel(
        var countries: List<Country> = emptyList(),
        val countryName: String = "",
        val radioName: String = ""
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
        val newModel = model.value.reducer()
        _model.value = newModel
        return newModel
    }

    fun onSearchByCountryName(countryName: String) {
        changeState { copy(countryName = countryName, countries = countryRepository.findByCountryNameLike(countryName)) }
        logger.atDebug().log("Buscó por nombre de país, buscando por $countryName")
    }

    fun onSearchByRadioName(text: String) {
        changeState { copy(radioName = text, countries = countryRepository.findByRadioNameLike(countryName)) }
        logger.atDebug().log("Buscó por nombre de radio, buscando por $text")
    }

    fun onDoubleclickInNode(node: Node<Country>){
        when (node) {
            is LeafNode<*> -> boxPlayerViewModel.onRadioSelected(node.content as Radio)
            is BranchNode<Country> -> toggleExpansionBranch(node)
        }
    }

    private fun toggleExpansionBranch(node: BranchNode<Country>){
        node.setExpanded(!node.isExpanded, 1 )
        println("${if(!node.isExpanded) "Expandido" else "Colapsado"} el nodo ${node.name}")
    }
}