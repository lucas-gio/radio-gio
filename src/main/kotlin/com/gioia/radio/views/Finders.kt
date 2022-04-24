package com.gioia.radio.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Finders(
    text:String,
    modifier: Modifier = Modifier,
    onTextChanged: (countryName: String)-> Unit
){
    Column(modifier = modifier){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = onTextChanged,
            label = {
                Text(text = "Nombre de país")
            }
        )
        //Spacer(Modifier.height(2.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = onTextChanged,
            label = {
                Text(text = "Nombre de la radio")
            }
        )
    }
}