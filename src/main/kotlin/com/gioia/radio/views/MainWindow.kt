package com.gioia.radio.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gioia.radio.config.di
import com.gioia.radio.views.viewModels.StationsViewModel
import org.kodein.di.instance

@Composable
@Preview
fun MainWindow() {
    val stationsViewModel: StationsViewModel by di.instance()

    Row(modifier = Modifier.fillMaxHeight().padding(16.dp)) {
        Column(
            Modifier.weight(1f)
        ) {
            BoxStations(stationsViewModel = stationsViewModel)
        }
        Column(
            Modifier.weight(1f)
        ) {
            BoxPlayer(stationsViewModel = stationsViewModel)
        }
    }
}