package com.gioia.radio.ui.screens.topAppBar

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.gioia.radio.ui.screens.stations.StationsViewModel

@Composable
fun NavigationTopBar(
    stationsViewModel: StationsViewModel
) {
    Text(text = stationsViewModel.model.value.selectedRadio?.name ?: "")
/*
    Button(
        icon = {
            Icon(
                imageVector = Icons.Default.Radio,
                contentDescription = "radios"
            )
        },
        label = { Text(text = "radios")},
        selected = activeComponent is RootComponent.Child.Radio,
        onClick = rootComponent::onRadioNavigationItem
    )*/
}