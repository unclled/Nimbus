package com.project.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val LightColors = NimbusColors(
    primaryContentColor = Color(0xFFFFFFFF),
    secondaryContentColor = Color(0xFF343434),
    largeAuthTitleColor = Color(0xD9FFFFFF)
)

val DarkColors = NimbusColors(
    primaryContentColor = Color(0xFFFFFFFF),
    secondaryContentColor = Color(0xFF343434),
    largeAuthTitleColor = Color(0xD9FFFFFF)
)

@Immutable
data class NimbusColors(
    val primaryContentColor: Color,
    val secondaryContentColor: Color,
    val largeAuthTitleColor: Color,
) {

}