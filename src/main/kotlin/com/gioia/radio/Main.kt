package com.gioia.radio

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.gioia.radio.config.DependencyInjectionTags
import com.gioia.radio.config.di
import com.gioia.radio.tools.DatabaseGenerator
import com.gioia.radio.ui.screens.root.Root
import com.gioia.radio.ui.screens.root.RootComponentImpl
import org.dizitart.no2.Nitrite
import org.kodein.di.constant
import org.kodein.di.instance
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent

@OptIn(ExperimentalDecomposeApi::class)
fun main(args: Array<String>) {
    val lifecycle = LifecycleRegistry()

    val rootComponent = RootComponentImpl(
        componentContext = DefaultComponentContext(lifecycle = lifecycle)
    )

    application {
        if (args.any { it == "initDatabase" }) {
            val databaseGenerator: DatabaseGenerator by di.instance()
            databaseGenerator.generateDatabase()
            return@application
        }

        val defaultWidth = 500.dp
        val defaultHeight = 900.dp

        val windowState = rememberWindowState(
            size = DpSize(defaultWidth, defaultHeight)
        )

        LifecycleController(lifecycle, windowState)

        Window(
            state = windowState,
            onCloseRequest = {
                releaseComponents()
                exitApplication()
            },
            title = "Radio Kotlin Compose"
        ) {
            //Surface(modifier = Modifier.fillMaxSize()) {
                MaterialTheme {
                    //CompositionLocalProvider(LocalScrollbarStyle provides defaultScrollbarStyle()) {
                        Root(
                            rootComponent = rootComponent
                        )
                   //}
                }
           // }
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