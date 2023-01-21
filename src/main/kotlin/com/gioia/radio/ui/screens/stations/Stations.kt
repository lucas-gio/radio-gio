package com.gioia.radio.ui.screens.stations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.data.domains.RadioStation
import com.gioia.radio.services.MessageService
import com.gioia.radio.ui.themes.AppTypography

@Composable
fun Stations(
    componentContext: ComponentContext,
    messageService: MessageService,
    stationsViewModel: StationsViewModel,
    onlyFavorites: Boolean
) {
    val state by stationsViewModel.model

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        /* stickyHeader {
             Text(text = country.name)
         }*/

        items(
            items = state.radioStations,
            { radioStation: RadioStation ->
                radioStation.name
            }
        ) { radioStation: RadioStation ->
            Row(
                modifier = Modifier
                    .clickable { stationsViewModel.onRadioSelected(radioStation) }
                    .fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp),)
                {

                    Text(text = radioStation.name, style = AppTypography.subtitle1)
                    radioStation.category?.let {
                        Text(
                            text = it,
                            style = AppTypography.body2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    radioStation.description?.let {
                        Text(
                            text = it,
                            style = AppTypography.subtitle2,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    OutlinedButton(
                        onClick = {  },
                        shape = CircleShape,
                    ) {
                        Icon(Icons.Rounded.ArrowForward ,contentDescription = "Radio info")
                    }
                    Divider()
                }
            }
        }
    }
}