package com.gioia.radio.ui.screens.stationDetail

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.gioia.radio.data.domains.RadioStation
import com.gioia.radio.ui.themes.AppTypography

@Composable
fun StationDetail(
    selectedStation: RadioStation,
    onFinished: () -> Unit
) {

    selectedStation.description?.let {
        Text(
            text = it,
            style = AppTypography.subtitle2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

    ExtendedFloatingActionButton(
        onClick = onFinished,
        icon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        },
        text = { Text(selectedStation.name) }
    )
}