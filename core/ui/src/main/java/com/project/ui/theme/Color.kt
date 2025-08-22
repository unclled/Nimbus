package com.project.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val accentColor = Color(0xE0FFC107)
val whiteColor = Color(0xFFFFFFFF)

val LightColors = NimbusColors(
    primaryContentColor = whiteColor,
    secondaryContentColor = Color(0xFFDBD9DC),
    largeAuthTitleColor = Color(0xD9FFFFFF),
    accentColor = accentColor,
    componentBackgroundColor = Color(0xCC011C3A),
    appNameFade = Brush.linearGradient(
        colors = listOf(
            accentColor,
            whiteColor
        )
    )
)

val DarkColors = NimbusColors(
    primaryContentColor = whiteColor,
    secondaryContentColor = Color(0xFF343434),
    largeAuthTitleColor = Color(0xD9FFFFFF),
    accentColor = accentColor,
    componentBackgroundColor = Color(0xFF343434),
    appNameFade = Brush.linearGradient(
        colors = listOf(
            accentColor,
            whiteColor
        )
    )
)

@Immutable
data class NimbusColors(
    val primaryContentColor: Color,
    val secondaryContentColor: Color,
    val largeAuthTitleColor: Color,
    val accentColor: Color,
    val componentBackgroundColor: Color,

    val appNameFade: Brush,
)