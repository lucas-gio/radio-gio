package com.gioia.radio.ui.toreview

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gioia.radio.data.domains.Country
import com.gioia.radio.data.domains.Radio

@Composable
fun ExpandableCountryRow(
    country: Country,
    onRadioClick: (Radio) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    if (isExpanded) {
        Divider()
    }

    countryRow(
        country = country
    ) {
        ExpandCollapseIcon(
            isExpanded = isExpanded,
            onExpandChanged = { isExpanded = !isExpanded }
        )
    }

    val modifier = if (isExpanded) Modifier.wrapContentHeight() else Modifier.height(0.dp)

    Column(modifier = modifier.animateContentSize()) {
        country.radios?.forEach {
            radioRow(
                radio = it,
                modifier = Modifier.clickable {
                    onRadioClick(it)
                }
            )
        }
    }

    if (isExpanded) {
        Divider()
    }
}