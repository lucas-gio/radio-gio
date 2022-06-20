package com.gioia.radio.ui.screens.settings

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
@Composable
fun Configuration(
    settingsComponent: SettingsComponent
) {
    ExtendedFloatingActionButton(
        onClick = settingsComponent.onBackPressed,
        icon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        },
        text = { Text("Volver a la pantalla principal") }
    )
}