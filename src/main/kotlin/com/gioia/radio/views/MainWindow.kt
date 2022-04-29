package com.gioia.radio.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun MainWindow() {
    MaterialTheme {
        Row(modifier = Modifier.padding(16.dp)){
            Column(modifier = Modifier){
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {  },
                    label = {
                        Text(text = "Nombre de país")
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {  },
                    label = {
                        Text(text = "Nombre de la radio")
                    }
                )
                RadiosTree(
                    modifier = Modifier,
                )
            }
        }
    }
}