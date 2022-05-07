package com.gioia.radio

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.gioia.radio.config.di
import com.gioia.radio.tools.DatabaseGenerator
import com.gioia.radio.views.MainWindow
import org.dizitart.no2.Nitrite
import org.kodein.di.compose.withDI
import org.kodein.di.constant
import org.kodein.di.instance

fun main(args: Array<String>) = application {
    if (args.any { it == "initDatabase" }) {
        val databaseGenerator: DatabaseGenerator by di.instance()
        databaseGenerator.generateDatabase()
        return@application
    }

    withDI(di) {
        val defaultWidth: Int by di.constant()
        val defaultHeight: Int by di.constant()

        Window(
            state = WindowState(
                size = DpSize(defaultWidth.dp, defaultHeight.dp)
            ),
            onCloseRequest = {
                val database: Nitrite by di.instance()
                if (!database.isClosed) {
                    database.close()
                }

                exitApplication()
            }) {
            MaterialTheme {
                MainWindow()
            }
        }
    }
}