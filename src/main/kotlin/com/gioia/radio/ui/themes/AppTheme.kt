package com.gioia.radio.ui.themes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


class Globals{
    companion object{
        /**
         * App's Light theme.
         */
        val LightTheme: Colors = lightColors()

        /**
         * App's Dark theme.
         */
        val DarkTheme: Colors = darkColors(
            primary = Color(0xff30A3E6),
            onPrimary = Color.White,
            secondary = Color(0xff0D2841),
            onSecondary = Color.White,
            surface = Color(0xff0D1D32),
            error = Color(0xffFF5370)
        )

        /**
         * Icon size of all application.
         */
        val iconSize: Modifier = Modifier.size(28.dp)
    }
}

@Composable
fun AppTheme(
    isDark: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    MaterialTheme(
        colors = if (isDark) Globals.DarkTheme else Globals.LightTheme,
        typography = AppTypography
    ) {
        Surface {
            Column {
                content()
            }
        }
    }
}