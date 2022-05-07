package com.gioia.radio.views.viewModels
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.gioia.radio.config.di
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.repositories.CountryRepository
import org.kodein.di.instance
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BoxStationsViewModel  {
    private val countryRepository: CountryRepository by di.instance()
    private var logger: Logger = LoggerFactory.getLogger(BoxStationsViewModel::class.java)

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

    private val _model = mutableStateOf(Model())
    val model: State<Model> = _model

    private inline fun changeState(reducer: Model.() -> Model): Model {
        _model.value = model.value.reducer()
        return _model.value
    }

    fun onSearchByCountryName(countryName: String) {
        changeState { copy(countryName = countryName) }
        logger.atDebug().log("Buscó por nombre de país, buscando por $countryName")

        changeState { copy(countries = countryRepository.findByCountryNameLike(countryName)) }
    }

    fun onSearchByRadioName(text: String) {
        changeState { copy(radioName = text) }
        logger.atDebug().log("Buscó por nombre de radio, buscando por $text")
    }

}