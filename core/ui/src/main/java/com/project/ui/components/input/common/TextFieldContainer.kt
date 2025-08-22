package com.project.ui.components.input.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.nimbus.core.ui.R
import com.project.ui.theme.NimbusTheme

@Composable
fun TextFieldContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .padding(top = dimensionResource(R.dimen.padding_4))
            .height(dimensionResource(R.dimen.text_field_height))
            .fillMaxWidth()
            .background(
                color = NimbusTheme.colors.componentBackgroundColor,
                shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius_24))
            )
            .border(
                width = dimensionResource(R.dimen.border_width_1),
                color = NimbusTheme.colors.accentColor.copy(alpha = 0.65f),
                shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius_24))
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        content()
    }
}

@Preview
@Composable
fun TextFieldContainerPreview() {
    NimbusTheme {
        TextFieldContainer {}
    }
}