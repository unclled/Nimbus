package com.project.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.nimbus.core.ui.R
import com.project.ui.theme.NimbusTheme

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(dimensionResource(R.dimen.button_width))
            .height(dimensionResource(R.dimen.button_height)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius_24)),
        colors = ButtonDefaults.buttonColors(
            containerColor = NimbusTheme.colors.accentColor.copy(alpha = 0.75f),
            contentColor = NimbusTheme.colors.componentBackgroundColor,
        ),
        border = BorderStroke(
            width = dimensionResource(R.dimen.border_width_1_5),
            color = NimbusTheme.colors.componentBackgroundColor
        ),
        elevation = null,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = NimbusTheme.typography.titleMedium.copy(
                    color = NimbusTheme.colors.primaryContentColor
                )
            )
            Spacer(Modifier.width(dimensionResource(R.dimen.padding_8)))
            Image(
                painter = painterResource(R.drawable.thunder),
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(R.dimen.thunder_icon_size))
            )
        }
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    NimbusTheme {
        CustomButton(text = "Вперед", onClick = {})
    }
}
