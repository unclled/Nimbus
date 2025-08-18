package com.project.auth.presentation.loading

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.project.nimbus.core.ui.R
import com.project.ui.theme.NimbusTheme
import com.project.ui.utils.Constants
import kotlinx.coroutines.delay

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LoadingScreen(
    navigateToAuth: () -> Unit
) {
    val locationPermissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        onPermissionResult = { isGranted ->
            navigateToAuth()
        }
    )

    val fullText = stringResource(R.string.hello_text)
    var animatedText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        delay(Constants.LETTER_ANIMATION_DELAY)
        fullText.forEachIndexed { index, _ ->
            animatedText = fullText.substring(0, index + 1)
            delay(Constants.LETTER_ANIMATION_DELAY)
        }
    }

    LaunchedEffect(Unit) {
        delay(Constants.LOADING_TO_AUTH_SCREEN_DELAY)
        if (locationPermissionState.status.isGranted) {
            navigateToAuth()
        } else {
            locationPermissionState.launchPermissionRequest()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.auth_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    renderEffect = BlurEffect(
                        radiusX = Constants.BACKGROUND_BLUR_RADIUS,
                        radiusY = Constants.BACKGROUND_BLUR_RADIUS
                    )
                },
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
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
                    style = NimbusTheme.typography.displayMedium,
                    color = NimbusTheme.colors.largeAuthTitleColor,
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


@Preview
@Composable
fun AuthScreenPreview() {
    NimbusTheme {
        LoadingScreen(
            navigateToAuth = {}
        )
    }
}
