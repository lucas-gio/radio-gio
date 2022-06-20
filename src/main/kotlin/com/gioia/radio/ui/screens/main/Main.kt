package com.gioia.radio.ui.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gioia.radio.services.MessageService

@Composable
fun Main(
/*componentContext: ComponentContext?,*/
    messageService: MessageService,
    mainComponent: MainComponent,
) {
    val state by mainComponent.model

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),

        ){

        state.countries.forEach{ country->
           /* stickyHeader {
                Text(text = country.name)
            }*/

            items(
                items = country.radios !!,
                /*{ radio: Radio ->
                    radio.name
                }*/
            ) { radio ->
                Row(
                    modifier = Modifier.clickable { mainComponent.onRadioSelected(radio) }
                ){
                    Column {
                        Text(text = radio.name, fontSize = 16.sp, fontWeight = FontWeight.Normal)
                        radio.category?.let { Text(text = it, fontSize = 14.sp, fontWeight = FontWeight.Normal, overflow = TextOverflow.Ellipsis) }
                        radio.description?.let { Text(text = it, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis) }
                    }
                }
            }
        }
    }
}