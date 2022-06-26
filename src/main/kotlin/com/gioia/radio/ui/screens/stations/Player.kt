package com.gioia.radio.ui.screens.stations

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gioia.radio.services.MessageService

@Composable
fun Player(
    stationsViewModel: StationsViewModel,
    messageService: MessageService,
){
    val state by stationsViewModel.model

    Column(modifier=Modifier){
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.padding(5.dp),
                enabled = !state.isPlaying && state.selectedRadio != null,
                onClick = stationsViewModel::onPlayPressed,
                border = BorderStroke(1.dp, Color.Black),

                ) {
                Icon(
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = messageService.msg("button.play")
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = stationsViewModel::onPreviousPressed,
                enabled = state.selectedRadio != null,
                border = BorderStroke(1.dp, Color.Black),

                ) {
                Icon(
                    imageVector = Icons.Rounded.SkipPrevious,
                    contentDescription = messageService.msg("button.previous")
                )
            }
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = stationsViewModel::onFavouritePressed,
                enabled = state.selectedRadio != null,
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Icon(
                    imageVector = if(state.isFavourite) Icons.Rounded.PlaylistAdd else Icons.Rounded.PlaylistAddCheck,
                    contentDescription = messageService.msg("button.addToFavourites")
                )
            }
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = stationsViewModel::onNextPressed,
                enabled = state.selectedRadio != null,
                border = BorderStroke(1.dp, Color.Black),

                ) {
                Icon(
                    imageVector = Icons.Rounded.SkipNext,
                    contentDescription = messageService.msg("button.next")
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = stationsViewModel::onStopPressed,
                enabled = state.isPlaying && state.selectedRadio != null,
                border = BorderStroke(1.dp, Color.Black),

                ) {
                Icon(
                    imageVector = Icons.Rounded.Stop,
                    contentDescription = messageService.msg("button.stop")
                )
            }
        }
        Slider(
            value = state.volume,
            valueRange = 0f..100f,
            onValueChange = stationsViewModel::onVolumeChange,
            onValueChangeFinished = stationsViewModel::onVolumeConfirmed
        )
        Text(
            modifier = Modifier.padding(5.dp),
            text = state.resume
        )
        Text(
            modifier = Modifier.padding(5.dp),
            text = state.selectedRadio?.category ?: ""
        )
        Text(
            modifier = Modifier.padding(5.dp),
            text = state.selectedRadio?.language ?: ""
        )
    }
}