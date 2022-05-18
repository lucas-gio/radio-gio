package com.gioia.radio.views.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.bonsai.core.node.BranchNode
import cafe.adriel.bonsai.core.node.LeafNode
import com.gioia.radio.config.di
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.data.repositories.CountryRepository
import org.kodein.di.instance
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent


class BoxStationsViewModel  {
    private val countryRepository: CountryRepository by di.instance()
    private var logger: Logger = LoggerFactory.getLogger(BoxStationsViewModel::class.java)

    private val _model = mutableStateOf(Model())
    val model: State<Model> = _model

    data class Model(
        var countries: List<Country> = emptyList(),
        val countryName: String = "",
        val radioName: String = ""
    ){
        init{
            if(countries.isEmpty()) {
                val countryRepository: CountryRepository by di.instance()
                countries = countryRepository.getInitialRadioStations()
            }
        }
    }

    private inline fun changeState(reducer: Model.() -> Model): Model {
        _model.value = model.value.reducer()
        return _model.value
    }

    fun toggleExpansionBranch(node: BranchNode<Country>){
        node.setExpanded(!node.isExpanded, 1 )
        println("${if(!node.isExpanded) "Expandido" else "Colapsado"} el nodo ${node.name}")
    }

    fun onSearchByCountryName(countryName: String) {
        changeState { copy(countries = countryRepository.findByCountryNameLike(countryName),
            countryName = countryName,
            radioName = model.value.radioName
            ) }
        logger.atDebug().log("Buscó por nombre de país, buscando por $countryName")

       // changeState { copy(countries = countryRepository.findByCountryNameLike(countryName)) }
    }

    fun onSearchByRadioName(text: String) {
        changeState { copy(radioName = text) }
        logger.atDebug().log("Buscó por nombre de radio, buscando por $text")
    }

    fun onRadioSelectedInTree(node: LeafNode<Radio>) {
        logger.atDebug().log("Seleccionada la radio ${node.name}")
        val audioPlayerComponent: AudioPlayerComponent by di.instance()
        audioPlayerComponent.mediaPlayer().media().play(node.content.url)
        logger.atDebug().log("Reproduciendo la radio ${node.name}")
    }
}