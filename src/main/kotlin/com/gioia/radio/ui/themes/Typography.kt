package com.gioia.radio.ui.themes

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp

val GoogleSans = FontFamily(
    Font("fonts/GoogleSans-Regular.ttf", FontWeight.Normal),
    Font("fonts/GoogleSans-Medium.ttf", FontWeight.Medium),
    Font("fonts/GoogleSans-Bold.ttf", FontWeight.Bold),
)

val AppTypography = Typography(

    defaultFontFamily = GoogleSans,

    h1 = TextStyle(
        fontSize = 95.sp,
        fontWeight = FontWeight.Normal,
    ),
    h2 = TextStyle(
        fontSize = 59.sp,
        fontWeight = FontWeight.Normal,
    ),
    h3 = TextStyle(
        fontSize = 48.sp,
        fontWeight = FontWeight.Medium
    ),
    h4 = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Medium,
    ),
    h5 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    h6 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    ),
    subtitle1 = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Medium,
        color = Color(245, 176, 65)
    ),
    subtitle2 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    body1 = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
    ),
    body2 = TextStyle(
        fontSize = 19.sp,
        fontWeight = FontWeight.Normal,
    ),
    button = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    ),
    caption = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
    ),
    overline = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
    )
)


