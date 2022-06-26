package com.gioia.radio.ui.screens.root.bottomAppBar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.gioia.radio.ui.navigation.RootComponent

@Composable
fun NavigationBottomBar(
    rootComponent: RootComponent
){
    val routerState by rootComponent.routerState.subscribeAsState()
    val activeComponent = routerState.activeChild.instance

    BottomNavigation {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Radio,
                    contentDescription = "radios"
                )
            },
            label = { Text(text = "radios")},
            selected = activeComponent is RootComponent.Child.Radio,
            onClick = rootComponent::onRadioNavigationItem
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search"
                )
            },
            label = { Text(text = "search")},
            selected = activeComponent is RootComponent.Child.Search,
            onClick = rootComponent::onSearchNavigationItem
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favorite"
                )
            },
            label = { Text(text = "favorite")},
            selected = activeComponent is RootComponent.Child.Favorites,
            onClick = rootComponent::onFavoriteNavigationItem
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "settings"
                )
            },
            label = { Text(text = "settings")},
            selected = activeComponent is RootComponent.Child.Settings,
            onClick = rootComponent::onSettingsNavigationItem
        )
    }
}