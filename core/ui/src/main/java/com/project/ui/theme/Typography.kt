package com.project.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.project.nimbus.core.ui.R

data class Typography(
    val displayLarge: TextStyle = TextStyle(
        fontFamily = annabelleCyrFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 90.sp,
        lineHeight = 90.sp
    ),

    val displayMedium: TextStyle = TextStyle(
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp,
        lineHeight = 40.sp
    ),

    val titleLarge: TextStyle = TextStyle(
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 20.sp
    ),

    val titleMedium: TextStyle = TextStyle(
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 20.sp
    ),

    val labelLarge: TextStyle = TextStyle(
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 16.sp
    ),

    val bodyMedium: TextStyle = TextStyle(
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 17.5.sp
    ),

    val bodySmall: TextStyle = TextStyle(
        fontFamily = ibmPlexSansFamily,
        fontWeight = FontWeight.Thin,
        fontSize = 12.sp,
        lineHeight = 12.sp
    ),
)

val annabelleCyrFamily = FontFamily(
    Font(R.font.annabelle_cyr, FontWeight.Normal)
)

val ibmPlexSansFamily = FontFamily(
    Font(R.font.ibm_plex_sans_extra_light, FontWeight.Thin),
    Font(R.font.ibm_plex_sans_light, FontWeight.Light),
    Font(R.font.ibm_plex_sans_regular, FontWeight.Normal),
    Font(R.font.ibm_plex_sans_semi_bold, FontWeight.SemiBold)
)
