package com.gioia.radio.views

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.gioia.radio.views.viewModels.BoxPlayerViewModel

@Composable
fun BoxPlayer(
    modifier: Modifier = Modifier,
    boxPlayerViewModel: BoxPlayerViewModel
){
    val state by boxPlayerViewModel.model

    Row(modifier=Modifier) {
        Button(
            onClick = boxPlayerViewModel::onPlayPressed,
            enabled = !state.isPlaying && state.radio != null
        ) {
            Text(
                text = "play"
            )
        }
        Button(
            onClick = boxPlayerViewModel::onStopPressed,
            enabled = state.isPlaying && state.radio != null
        ) {
            Text(
                text = "stop"
            )
        }
    }
}