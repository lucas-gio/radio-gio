package com.gioia.radio.views

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
import cafe.adriel.bonsai.core.Bonsai
import cafe.adriel.bonsai.core.node.Branch
import cafe.adriel.bonsai.core.node.Leaf
import cafe.adriel.bonsai.core.node.Node
import cafe.adriel.bonsai.core.tree.Tree
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.services.MessageService
import com.gioia.radio.views.viewModels.StationsViewModel

@Composable
fun BoxStations(
    stationsViewModel: StationsViewModel,
    messageService: MessageService
){
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
    Bonsai(
        modifier = Modifier,
        tree = Tree {
            state.countries.forEach { country: Country ->
                Branch(
                    name = country.name,
                    content = country,
                ) {
                    country.radios?.forEach { radio: Radio ->
                        Leaf(
                            name = radio.name,
                            content = radio
                        )
                    }
                }
            }
        },
        onDoubleClick = {node: Node<Country> -> stationsViewModel.onDoubleclickInNode(node as Node<Any>) }
    )
}