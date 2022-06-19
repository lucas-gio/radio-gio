package com.gioia.radio.ui.screens.common

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.gioia.radio.config.DependencyInjectionTags
import com.gioia.radio.config.dk
import org.kodein.di.instance

@Composable
fun NavigationBar(){
    BottomNavigation {
        val items: List<NavigationItem> = dk.instance(DependencyInjectionTags.NavigationItems.toString())
        items.forEach {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title//stringResource(id = it.titleResId)
                    )
                },
                label = { Text(text = it.title /*stringResource(id = item.titleResId)*/) },
                selected = false, //TODO
                onClick = {}
            )
        }
    }
}