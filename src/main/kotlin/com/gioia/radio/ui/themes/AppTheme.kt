package com.gioia.radio.ui.themes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Color set
val LightTheme = lightColors() // TODO :
val DarkTheme = darkColors(
    primary = Color(0xff30A3E6),
    onPrimary = Color.White,
    secondary = Color(0xff0D2841),
    onSecondary = Color.White,
    surface = Color(0xff0D1D32),
    error = Color(0xffFF5370)
)

@Composable
fun AppTheme(
    isDark: Boolean = true, //fixme
    content: @Composable ColumnScope.() -> Unit,
) {
    MaterialTheme(
        colors = if (isDark) DarkTheme else LightTheme,
        typography = AppTypography
    ) {
        Surface {
            Column {
                content()
            }
        }
    }
}