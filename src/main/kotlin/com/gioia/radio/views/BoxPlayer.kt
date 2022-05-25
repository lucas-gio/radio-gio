package com.gioia.radio.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gioia.radio.views.viewModels.StationsViewModel

@Composable
fun BoxPlayer(
    stationsViewModel: StationsViewModel
){
    val state by stationsViewModel.model

    Row(modifier=Modifier) {
        Button(
            enabled = !state.isPlaying && state.selectedRadio != null,
            onClick = stationsViewModel::onPlayPressed,
            border = BorderStroke(1.dp, Color.Black), //fixme: Pasar a un tema
            elevation =  ButtonDefaults.elevation(    //fixme: Pasar a un tema
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.PlayArrow,
                contentDescription = "play" //fixme: Traducciones
            )
        }
        Button(
            onClick = stationsViewModel::onStopPressed,
            enabled = state.isPlaying && state.selectedRadio != null,
            border = BorderStroke(1.dp, Color.Black),
            elevation =  ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Stop,
                contentDescription = "stop" //fixme: Traducciones
            )
        }
        Button(
            onClick = stationsViewModel::onFavouritePressed,
            enabled = state.selectedRadio != null,
            border = BorderStroke(1.dp, Color.Black),
            elevation =  ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            )
        ) {
            Icon(
                imageVector = if(state.isFavourite) Icons.Rounded.PlaylistAdd else Icons.Rounded.PlaylistAddCheck,
                contentDescription = "add to favourite" //fixme: Traducciones
            )
        }
        Button(
            onClick = stationsViewModel::onPreviousPressed,
            enabled = state.selectedRadio != null,
            border = BorderStroke(1.dp, Color.Black),
            elevation =  ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.SkipPrevious,
                contentDescription = "Previous" //fixme: Traducciones
            )
        }
        Button(
            onClick = stationsViewModel::onNextPressed,
            enabled = state.selectedRadio != null,
            border = BorderStroke(1.dp, Color.Black),
            elevation =  ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.SkipNext,
                contentDescription = "Next" //fixme: Traducciones
            )
        }
    }
}