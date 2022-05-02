package com.gioia.radio.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun StationsViewModel(modifier: Modifier = Modifier){
    var countryName by remember { mutableStateOf("") }
    var radioName by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = countryName,
        onValueChange = { countryName = it  },
        label = {
            Text(text = "Nombre de país")
        }
    )
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = radioName,
        onValueChange = { radioName = it },
        label = {
            Text(text = "Nombre de la radio")
        }
    )
    RadiosTree(
        modifier = Modifier,
    )
}