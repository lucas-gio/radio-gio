package com.gioia.radio.ui.screens.topAppBar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.gioia.radio.services.MessageService
import com.gioia.radio.ui.screens.stations.StationsViewModel

@Composable
fun NavigationTopBar(
    stationsViewModel: StationsViewModel,
    messageService: MessageService,
) {
    val state by stationsViewModel.model

    Text( text = state.selectedRadio?.name ?: "" )

    IconButton(
        enabled = !state.isPlaying && state.selectedRadio != null,
        onClick = stationsViewModel::onPlayPressed
    ) {
        Icon(
            imageVector = Icons.Rounded.PlayArrow,
            contentDescription = messageService.msg("button.play")
        )
    }

    IconButton(
        onClick = stationsViewModel::onStopPressed,
        enabled = state.isPlaying && state.selectedRadio != null,
    ) {
        Icon(
            imageVector = Icons.Rounded.Stop,
            contentDescription = messageService.msg("button.stop")
        )
    }
}