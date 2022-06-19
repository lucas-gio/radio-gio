package com.gioia.radio.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.services.MessageService

@Composable
fun Main(/*componentContext: ComponentContext?,*/
         messageService: MessageService,
         mainComponent: MainComponent,
) {
    Row(modifier = Modifier.fillMaxHeight().padding(16.dp)) {
        Column(
            Modifier.weight(1f)
        ) {
            Button(
                onClick = mainComponent.onConfigPressed
            ){
                Text("Ir a configuración")
            }
            Stations(mainComponent = mainComponent,
                messageService = messageService)
        }
        Column(
            Modifier.weight(1f)
        ) {
            Player(mainComponent = mainComponent,
                messageService = messageService)
        }
    }
}