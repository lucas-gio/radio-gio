package com.gioia.radio.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp
import com.gioia.radio.config.di
import com.gioia.radio.views.viewModels.BoxPlayerViewModel
import com.gioia.radio.views.viewModels.BoxStationsViewModel
import org.kodein.di.instance

@Composable
@Preview
fun MainWindow() {
    val boxStationsViewModel: BoxStationsViewModel by di.instance()
    val boxPlayerViewModel: BoxPlayerViewModel by di.instance()

    Row(modifier = Modifier.fillMaxHeight().padding(16.dp)) {
        Column(
            Modifier.weight(1f)
        ) {
            BoxStations(boxStationsViewModel = boxStationsViewModel)
        }
        Column(
            Modifier.weight(1f).background(Yellow)
        ) {
            BoxPlayer(boxPlayerViewModel = boxPlayerViewModel)
        }
    }
}