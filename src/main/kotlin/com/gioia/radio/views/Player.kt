package com.gioia.radio.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Player(modifier: Modifier){
    OutlinedTextField(
        modifier = modifier,
        value = "",
        onValueChange = {  },
        label = {
            Text(text = "Nombre de país")
        }
    )
    OutlinedTextField(
        modifier = modifier,
        value = "",
        onValueChange = {  },
        label = {
            Text(text = "Nombre de la radio")
        }
    )
    RadiosTree(
        modifier = modifier,
    )
}