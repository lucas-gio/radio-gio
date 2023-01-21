package com.gioia.radio

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.gioia.radio.App.Companion.appName
import com.gioia.radio.App.Companion.appVersion
import com.gioia.radio.App.Companion.isInitDatabase
import com.gioia.radio.App.Companion.releaseComponents
import com.gioia.radio.config.di
import com.gioia.radio.tools.DatabaseGenerator
import com.gioia.radio.ui.navigation.Root
import com.gioia.radio.ui.navigation.RootComponentImpl
import com.gioia.radio.ui.themes.AppTheme
import org.dizitart.no2.Nitrite
import org.kodein.di.instance
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent

class App{
    companion object{
        var isInitDatabase: Boolean = false
        var appName = "Radio Gio by Lucas Gioia"
        var appVersion = "v0.1"

        fun releaseComponents(){
            val audioPlayerComponent: AudioPlayerComponent by di.instance()

            val database: Nitrite by di.instance()
            if (!database.isClosed) {
                database.close()
            }

            audioPlayerComponent.release()
        }
    }
}

@OptIn(ExperimentalDecomposeApi::class)
fun main(args: Array<String>) {
    isInitDatabase = args.any { it == "initDatabase" }

    val lifecycle = LifecycleRegistry()

    val rootComponent = RootComponentImpl(
        componentContext = DefaultComponentContext(lifecycle = lifecycle)
    )

    application {
        if (isInitDatabase) {
            val databaseGenerator: DatabaseGenerator by di.instance()
            databaseGenerator.generateDatabase()
            return@application
        }

        val defaultWidth = 550.dp
        val defaultHeight = 700.dp

        val windowState = rememberWindowState(
            size = DpSize(defaultWidth, defaultHeight)
        )

        LifecycleController(lifecycle, windowState)

        Window(
            state = windowState,
            icon = painterResource("drawables/launcher_icons/system.png"),
            onCloseRequest = {
                releaseComponents()
                exitApplication()
            },
            title = "$appName $appVersion"
        ) {
            AppTheme {
                Root(
                    rootComponent = rootComponent
                )
            }
        }
    }
}

