package com.gioia.radio.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.data.domains.Country
import com.gioia.radio.ui.screens.stations.StationsViewModel
import com.gioia.radio.ui.themes.AppTypography

@Composable
fun Search(
    componentContext: ComponentContext,
    stationsViewModel: StationsViewModel
) {
    val state by stationsViewModel.model

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        items(
            items = state.countries,
            key = { country: Country ->
                country.code
            }
        ) { country: Country ->
            Row(
                modifier = Modifier
                    .clickable { stationsViewModel.onCountrySelected(country.code) }
                    .fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp))
                {
                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp)){

                        Column() {
                            Card(
                                modifier = Modifier.size(25.dp),
                                shape = CircleShape,
                            ) {
                                Image(
                                    painter = painterResource("drawables/countries/${country.code}.png"),
                                    "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.border(1.dp, Color.Black, CircleShape)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.size(5.dp))

                        Column() {
                            Text(text = country.code, style = AppTypography.subtitle1)
                        }
                    }

                    Divider()
                }
            }
        }
    }
}