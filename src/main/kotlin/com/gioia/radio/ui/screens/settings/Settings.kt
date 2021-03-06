package com.gioia.radio.ui.screens.settings

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext

@Composable
fun Settings(
    componentContext: ComponentContext,
    settingsViewModel: SettingsViewModel
) {
    ExtendedFloatingActionButton(
        onClick = settingsViewModel.onBackPressed,
        icon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        },
        text = { Text("Volver a la pantalla principal") }
    )
}