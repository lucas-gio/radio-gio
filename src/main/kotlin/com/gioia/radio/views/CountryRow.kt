package com.gioia.radio.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gioia.radio.data.domains.Country

@Composable
fun countryRow(
    country: Country,
    modifier: Modifier = Modifier,
    control: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = country.name,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(
                Alignment.CenterStart
            )
        )
        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            control()
        }
    }

}