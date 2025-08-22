package com.project.ui.components.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.nimbus.core.ui.R
import com.project.ui.components.background.BackgroundImage
import com.project.ui.theme.NimbusTheme

@Composable
fun LoadingScreenContent(
    animatedText: String
) {
    BackgroundImage {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.nimbus_icon_bright),
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(R.dimen.auth_app_icon_size))
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_8)))
                Text(
                    text = stringResource(R.string.app_name),
                    style = NimbusTheme.typography.displayMedium.copy(
                        brush = NimbusTheme.colors.appNameFade
                    ),
                )

            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_12)))
            Text(
                text = animatedText,
                style = NimbusTheme.typography.displayLarge,
                color = NimbusTheme.colors.largeAuthTitleColor,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    NimbusTheme {
        LoadingScreenContent(
            animatedText = "Привет"
        )
    }
}