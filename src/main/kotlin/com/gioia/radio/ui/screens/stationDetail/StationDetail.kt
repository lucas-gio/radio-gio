package com.gioia.radio.ui.screens.stationDetail

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.gioia.radio.data.domains.RadioStation

@Composable
fun StationDetail(
    selectedStation: RadioStation
) {
    ExtendedFloatingActionButton(
        onClick = {  },
        icon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        },
        text = { Text(selectedStation.name) }
    )
}