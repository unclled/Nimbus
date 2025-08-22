package com.project.auth.presentation.auth

import android.Manifest
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.project.nimbus.core.ui.R
import com.project.nimbus.presentation.WeatherViewModel
import com.project.ui.components.BackgroundImage
import com.project.ui.theme.NimbusTheme
import org.koin.compose.koinInject

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AuthScreen(
    viewModel: WeatherViewModel = koinInject()
) {
    val locationPermissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(Unit) {
        viewModel.loadWeather()
    }

    BackgroundImage {
        if (locationPermissionState.status.isGranted) {
            ScreenBody(
                title = { TitleText(stringResource(R.string.location_detected)) },
                body = { },
                footer = { }
            )
        } else {
            ScreenBody(
                title = { TitleText(stringResource(R.string.location_not_detected)) },
                body = { },
                footer = { }
            )
        }
    }

}

@Composable
private fun TitleText(
    text: String
) {
    Text(
        text = text,
        style = NimbusTheme.typography.displayMedium,
        color = NimbusTheme.colors.primaryContentColor
    )
}

@Composable
private fun ScreenBody(
    title: @Composable () -> Unit,
    body: @Composable () -> Unit,
    footer: @Composable () -> Unit
) {
    title()
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_12)))
    body()
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_12)))
    footer()
}

@Preview
@Composable
fun AuthScreenPreview() {
    NimbusTheme {
        AuthScreen()
    }
}