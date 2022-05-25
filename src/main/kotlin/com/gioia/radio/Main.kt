package com.gioia.radio

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.gioia.radio.config.di
import com.gioia.radio.services.MessageService
import com.gioia.radio.tools.DatabaseGenerator
import com.gioia.radio.views.MainWindow
import com.gioia.radio.views.viewModels.StationsViewModel
import org.dizitart.no2.Nitrite
import org.kodein.di.constant
import org.kodein.di.instance
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent

fun main(args: Array<String>) = application {
    if (args.any { it == "initDatabase" }) {
        val databaseGenerator: DatabaseGenerator by di.instance()
        databaseGenerator.generateDatabase()
        return@application
    }

    val defaultWidth: Int by di.constant()
    val defaultHeight: Int by di.constant()
    val messageService: MessageService by di.instance()
    val stationsViewModel: StationsViewModel by di.instance()

    Window(
        state = WindowState(
            size = DpSize(defaultWidth.dp, defaultHeight.dp)
        ),
        onCloseRequest = {
            releaseComponents()
            exitApplication()
        }
    ) {
        MaterialTheme {
            MainWindow(messageService = messageService,
                       stationsViewModel = stationsViewModel)
        }
    }
}

fun releaseComponents(){
    val audioPlayerComponent: AudioPlayerComponent by di.instance()

    val database: Nitrite by di.instance()
    if (!database.isClosed) {
        database.close()
    }

    audioPlayerComponent.release()
}