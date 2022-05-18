package com.gioia.radio.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.bonsai.core.Bonsai
import cafe.adriel.bonsai.core.node.*
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
        onDoubleClick = {node: Node<Country> ->
            when (node) {
                is LeafNode -> boxStationsViewModel.onRadioSelectedInTree(node as LeafNode<Radio>)
                is BranchNode -> boxStationsViewModel.toggleExpansionBranch(node)
            }
        }
    )
}