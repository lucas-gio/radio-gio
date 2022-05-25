package com.gioia.radio.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
import com.gioia.radio.views.viewModels.StationsViewModel

@Composable
fun BoxStations(
    stationsViewModel: StationsViewModel
){
    val state by stationsViewModel.model

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = state.countryFilter,
        onValueChange = stationsViewModel::onSearchByCountryName,
        label = {
            Text(text = "Nombre de país")
        }
    )
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = state.radioFilter,
        onValueChange = stationsViewModel::onSearchByRadioName,
        label = {
            Text(text = "Nombre de la radio")
        }
    )
    Bonsai(
        tree = Tree {
            state.countries.forEach { country: Country ->
                // fixme: Cambiar el ícono a cada país. Guardarlo en la bd.
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
        onDoubleClick = {node: Node<Country> -> stationsViewModel.onDoubleclickInNode(node) }
    )
}