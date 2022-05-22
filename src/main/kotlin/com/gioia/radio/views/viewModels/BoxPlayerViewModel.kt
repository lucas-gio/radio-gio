package com.gioia.radio.views.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.services.PlayerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class BoxPlayerViewModel(
    private val playerService: PlayerService
){
  //  private val stateKeeper: StateKeeper by di.instance()
    private var logger: Logger = LoggerFactory.getLogger(BoxPlayerViewModel::class.java)

    // Si statekeeper contiene el valor almacenado, entonces lo usa, sino usa un nuevo objeto Model.
    private val _model = mutableStateOf(/*stateKeeper.consume(boxStationsState) ?: */PlayerModel())
    val model: State<PlayerModel> = _model

   /* @Parcelize*/ // Preserva el estado cuando se rota una pantalla android.
    data class PlayerModel(
        val isPlaying: Boolean = false, //fixme: En un futuro podría ser configurable
        val radio: Radio? = null
    )/*: Parcelable */

   /* init {
        stateKeeper.register(boxStationsState, listState::value)
    }*/

    private inline fun changeState(reducer: PlayerModel.() -> PlayerModel): PlayerModel {
        val newModel = model.value.reducer()
        _model.value = newModel
        return newModel
    }

    private fun isRadioInMemory(): Boolean{
        return model.value.radio != null
    }

    private fun modelRadio(): Radio {
        return model.value.radio as Radio
    }

    fun onPlayPressed(isPlaying: Boolean? = null, radio: Radio? = null){
        changeState { copy(isPlaying = isPlaying ?: !model.value.isPlaying, radio = radio ?: modelRadio()) }
        playerService.playRadio(modelRadio().url) // fixme: hay varias url, debe considerarlas a todas si una falla. Además cada una de ellas da diferente calidad.
        logger.atDebug().log("Reproduciendo la radio ${modelRadio().name}")
    }

    fun onStopPressed(){
        changeState { copy(isPlaying = !model.value.isPlaying, radio = modelRadio()) }
        playerService.stopRadio()
        logger.atDebug().log("Apagando la radio ${modelRadio().name}")
    }

    fun onRadioSelected(radio: Radio) {
        if(isRadioInMemory() && radio.name == modelRadio().name) return
        logger.atDebug().log("Seleccionada la radio ${radio.name}")
        onPlayPressed(true, radio)
    }
}