package com.gioia.radio.ui.screens.topAppBar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.gioia.radio.services.MessageService
import com.gioia.radio.ui.screens.stations.StationsViewModel
import com.gioia.radio.ui.themes.Globals

@Composable
fun NavigationTopBar(
    stationsViewModel: StationsViewModel,
    messageService: MessageService,
) {
    val state by stationsViewModel.model

    Text(
        text = state.selectedRadioStation?.name ?: "",
        overflow = TextOverflow.Ellipsis
    )

    IconButton(
        enabled = !state.isPlaying && state.selectedRadioStation != null,
        onClick = stationsViewModel::onPlayPressed
    ) {
        Icon(
            imageVector = Icons.Rounded.PlayArrow,
            contentDescription = messageService.msg("button.play"),
            modifier = Globals.iconSize
        )
    }

    IconButton(
        onClick = stationsViewModel::onStopPressed,
        enabled = state.isPlaying && state.selectedRadioStation != null,
    ) {
        Icon(
            imageVector = Icons.Rounded.Stop,
            contentDescription = messageService.msg("button.stop"),
            modifier = Globals.iconSize
        )
    }

    IconButton(
        onClick = stationsViewModel::onFavouritePressed
    ) {
        Icon(
            imageVector = Icons.Rounded.Favorite,
            contentDescription = messageService.msg("button.addToFavourite"),
            tint = if(state.isFavourite) Color.Red else Color.White,
            modifier = Globals.iconSize
        )
    }
/*
    Slider(
        value = state.volume,
        valueRange = 0f..100f,
        onValueChange = stationsViewModel::onVolumeChange,
        onValueChangeFinished = stationsViewModel::onVolumeConfirmed
    )
    Text(
        modifier = Modifier.padding(5.dp),
        text = state.resume
    )*/
}