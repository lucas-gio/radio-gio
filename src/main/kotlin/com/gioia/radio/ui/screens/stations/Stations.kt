package com.gioia.radio.ui.screens.stations

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gioia.radio.data.domains.RadioStation
import com.gioia.radio.ui.themes.AppTypography

@Composable
fun Stations(
    stationsViewModel: StationsViewModel,
    whenDetails: (RadioStation) -> Unit
) {
    val state by stationsViewModel.model

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
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
                Column(verticalArrangement = Arrangement.spacedBy(10.dp))
                {

                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp)){
                        Card(
                            modifier = Modifier.size(45.dp),
                            shape = CircleShape,
                        ) {
                            Image(
                                imageVector = Icons.Default.Search,
                                "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(20.dp).border(1.dp, Color.Black, CircleShape)
                            )
                        }

                        Text(text = radioStation.name, style = AppTypography.subtitle1)
                    }
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
                        onClick = {whenDetails(radioStation)} /*{
                            App.rootComponent.onStationDetail(radioStation)
                        }*/,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Icon(
                            Icons.Default.DoubleArrow ,
                            contentDescription = "Radio info",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Divider()
                }
            }
        }
    }
}