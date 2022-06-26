package com.gioia.radio.ui.toreview

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun ExpandCollapseIcon(
    isExpanded: Boolean,
    onExpandChanged: () -> Unit
) {
    IconButton(
        onClick = onExpandChanged,
    ) {
        Icon(
            Icons.Filled.ArrowDropDown,
            "",
            Modifier.rotate(if (isExpanded) 180f else 360f)
        )
    }
}