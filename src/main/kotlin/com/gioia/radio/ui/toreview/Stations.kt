package com.gioia.radio.ui.toreview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.gioia.radio.services.MessageService
import com.gioia.radio.ui.screens.stations.StationsViewModel

@Composable
fun Stations(
    stationsViewModel: StationsViewModel,
    messageService: MessageService
) {
    val state by stationsViewModel.model

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = state.countryFilter,
        onValueChange = stationsViewModel::onSearchByCountryName,
        label = {
            Text(text = messageService.msg("search.countryName"))
        },
        trailingIcon = {
            if (state.countryFilter.isNotBlank())
                IconButton(onClick = stationsViewModel::onClearCountryFilter) {
                    Icon(
                        imageVector = Icons.Rounded.Cancel,
                        contentDescription = messageService.msg("search.countryName.clear")
                    )
                }
        }
    )
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = state.radioFilter,
        onValueChange = stationsViewModel::onSearchByRadioName,
        label = {
            Text(text = messageService.msg("search.radioName"))
        },
        trailingIcon = {
            if (state.radioFilter.isNotBlank())
                IconButton(onClick = stationsViewModel::onClearRadioFilter) {
                    Icon(
                        imageVector = Icons.Rounded.Cancel,
                        contentDescription = messageService.msg("search.radioName.clear")
                    )
                }
        }
    )
    Column {
        state.countries.forEach {
            ExpandableCountryRow(
                country = it,
                onRadioClick = stationsViewModel::onRadioSelected
            )
        }
    }
}