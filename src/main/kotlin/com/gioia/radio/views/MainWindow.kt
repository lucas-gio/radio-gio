package com.gioia.radio.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gioia.radio.services.MessageService
import com.gioia.radio.views.viewModels.StationsViewModel

@Composable
@Preview
fun MainWindow(messageService: MessageService,
               stationsViewModel: StationsViewModel) {
    Row(modifier = Modifier.fillMaxHeight().padding(16.dp)) {
        Column(
            Modifier.weight(1f)
        ) {
            BoxStations(stationsViewModel = stationsViewModel,
                messageService = messageService)
        }
        Column(
            Modifier.weight(1f)
        ) {
            BoxPlayer(stationsViewModel = stationsViewModel,
                messageService = messageService)
        }
    }
}