package com.gioia.radio.ui.screens.stations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.services.MessageService
import com.gioia.radio.ui.themes.AppTypography

@Composable
fun Stations(
    componentContext: ComponentContext,
    messageService: MessageService,
    stationsViewModel: StationsViewModel,
) {
    val state by stationsViewModel.model

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
                { radio: Radio ->
                    radio.name
                }
            ) { radio: Radio ->
                Row(
                    modifier = Modifier
                        .clickable { stationsViewModel.onRadioSelected(radio) }
                        .fillMaxWidth()
                ){
                    Column {
                        Text(text = radio.name, style = AppTypography.subtitle1)
                        radio.category?.let { Text(text = it, style = AppTypography.body2, overflow = TextOverflow.Ellipsis) }
                        radio.description?.let { Text(text = it, style = AppTypography.caption, maxLines = 1, overflow = TextOverflow.Ellipsis) }
                    }
                }
            }
        }
    }
}