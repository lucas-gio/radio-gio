package com.gioia.radio.views.viewModels
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.bonsai.core.node.LeafNode
import com.gioia.radio.config.di
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.data.repositories.CountryRepository
import org.kodein.di.instance
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.SystemColor.info
import java.io.IOException
import java.net.URL
import javax.sound.sampled.*


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

    fun onRadioSelectedInTree(node: LeafNode<Radio>) {
        logger.atDebug().log("Seleccionada la radio ${node.name}")

        var stream: AudioInputStream =
            AudioSystem.getAudioInputStream(URL(node.content.url))
        var format: AudioFormat = stream.format

        if (format.encoding != AudioFormat.Encoding.PCM_SIGNED) {
            format = AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED, format.sampleRate, format.sampleSizeInBits * 2, format
                    .channels, format.frameSize * 2, format.frameRate,
                true
            )
            stream = AudioSystem.getAudioInputStream(format, stream)
        }

        val info: DataLine.Info =
            DataLine.Info(SourceDataLine::class.java, stream.format, ((stream.frameLength * format.frameSize).toInt()))
        val line: SourceDataLine = AudioSystem.getLine(info) as SourceDataLine
        line.open(stream.format)
        line.start()

        val buf = ByteArray(line.bufferSize)
        var numRead = stream.read(buf, 0, buf.size)
        while (numRead >= 0) {
            var offset = 0
            while (offset < numRead) {
                offset += line.write(buf, offset, numRead - offset)
            }
            numRead = stream.read(buf, 0, buf.size)
        }
        line.drain()
        line.stop()
    }
}