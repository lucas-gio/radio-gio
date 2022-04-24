package com.gioia.radio

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.gioia.radio.config.di
import com.gioia.radio.tools.DatabaseGenerator
import com.gioia.radio.views.MainWindow
import org.kodein.di.compose.withDI

fun main(args: Array<String>) = application {
    if (args.any { it == "initDatabase" }) {
        DatabaseGenerator.generateDatabase()
    }

    withDI(di) {
        Window(onCloseRequest = ::exitApplication) {
            MainWindow()
        }
    }
}