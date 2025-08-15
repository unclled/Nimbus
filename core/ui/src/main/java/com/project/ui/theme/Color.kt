package com.project.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val LightColors = NimbusColors(
    primaryContentColor = Color(0xFFFFFFFF)
)

val DarkColors = NimbusColors(
    primaryContentColor = Color(0xFFFFFFFF)
)

@Immutable
data class NimbusColors(
    val primaryContentColor: Color
) {

}