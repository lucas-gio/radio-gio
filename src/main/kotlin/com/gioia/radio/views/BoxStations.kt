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
import cafe.adriel.bonsai.core.tree.Tree
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.Radio
import com.gioia.radio.views.viewModels.BoxStationsViewModel

@Composable
fun BoxStations(
    modifier: Modifier = Modifier,
    boxStationsViewModel: BoxStationsViewModel
){
    val state by boxStationsViewModel.model

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = state.countryName,
        onValueChange = boxStationsViewModel::onSearchByCountryName,
        label = {
            Text(text = "Nombre de país")
        }
    )
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = state.radioName,
        onValueChange = boxStationsViewModel::onSearchByRadioName,
        label = {
            Text(text = "Nombre de la radio")
        }
    )
    Bonsai(
        modifier = Modifier,
        tree = BonsaiBranches(state.countries)
    )
}

@Composable
fun BonsaiBranches(countries: List<Country>): Tree<String> {
    val tree = Tree<String> {
        countries
            .forEach { country: Country ->
                // fixme: Cambiar el ícono a cada país. Guardarlo en la bd.
                Branch(country.name) {
                    country
                        .radios
                        ?.forEach { radio: Radio ->
                            Leaf(radio.name)
                        }
                }
            }
    }

    return tree
}