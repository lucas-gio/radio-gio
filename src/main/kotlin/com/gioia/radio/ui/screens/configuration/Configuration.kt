package com.gioia.radio.ui.screens.configuration

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
@Composable
fun Configuration(
    configurationComponent: ConfigurationComponent
) {
    Button(
        onClick = configurationComponent.onBackPressed
    ){
        Text("Volver a la pantalla principal")
    }
}