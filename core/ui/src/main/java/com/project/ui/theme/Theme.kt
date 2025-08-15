package com.project.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalTypography = staticCompositionLocalOf {
    Typography()
}

object NimbusTheme {
    val colors: NimbusColors
        @Composable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        get() = LocalTypography.current
}

val LocalColors = staticCompositionLocalOf { LightColors }


@Composable
fun NimbusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: Typography = NimbusTheme.typography,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        content = content
    )
}