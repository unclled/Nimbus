package com.project.auth.presentation.loading

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.project.nimbus.core.ui.R
import com.project.ui.components.screen.LoadingScreenContent
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
    
    LoadingScreenContent(animatedText = animatedText)
}
