package com.project.auth.presentation.auth

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.project.nimbus.core.ui.R
import com.project.nimbus.presentation.WeatherViewModel
import com.project.ui.components.background.BackgroundImage
import com.project.ui.components.button.CustomButton
import com.project.ui.components.input.CustomTextField
import com.project.ui.theme.NimbusTheme
import com.project.ui.utils.StyledSentenceText
import org.koin.compose.koinInject

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AuthScreen(
    viewModel: WeatherViewModel = koinInject()
) {
    var showManualInput by remember { mutableStateOf(false) }
    val state by viewModel.state.collectAsState()
    val locationPermissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(Unit) {
        viewModel.loadWeather()
    }

    BackgroundImage {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showManualInput) {
                RequestFailed()
            } else if (locationPermissionState.status.isGranted) {
                ScreenBody(
                    title = {
                        StyledSentenceText(
                            fullText = stringResource(R.string.location_detected),
                            wordsToStyle = listOf(
                                stringResource(R.string.detect),
                                stringResource(R.string.location)
                            ),
                            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_20))
                        )
                    },
                    body = {
                        when {
                            state.isLoading -> CircularProgressIndicator()
                            state.address != null -> BodyText(text = state.address.toString())
                            state.error != null -> BodyText(text = state.error.toString())
                            else -> RequestFailed()
                        }
                    },
                    footer = {
                        val annotatedString = buildAnnotatedString {
                            append(stringResource(R.string.not_your_location))
                            append(" ")

                            val link = LinkAnnotation.Clickable(
                                tag = stringResource(R.string.change_location),
                                linkInteractionListener = {
                                    showManualInput = true
                                }
                            )

                            pushLink(link)
                            withStyle(
                                style = SpanStyle(
                                    color = NimbusTheme.colors.accentColor,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append(stringResource(R.string.change_location))
                            }
                            pop()
                        }

                        Text(
                            text = annotatedString,
                            style = NimbusTheme.typography.labelLarge.copy(
                                color = NimbusTheme.colors.secondaryContentColor,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                )
            } else {
                RequestFailed()
            }
        }

        CustomButton(
            onClick = {},
            text = stringResource(R.string.to_next_page),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = dimensionResource(R.dimen.padding_64))
        )
    }
}

@Composable
private fun RequestFailed() {
    ScreenBody(
        title = {
            StyledSentenceText(
                fullText = stringResource(R.string.location_not_detected),
                wordsToStyle = listOf(
                    stringResource(R.string.not),
                    stringResource(R.string.detect),
                    stringResource(R.string.location)
                ),
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_20))
            )
        },
        body = {
            CustomTextField(
                placeholderText = stringResource(R.string.enter_your_location),
                inputText = "",
                onValueChange = {},
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_32))
            )
        },
        footer = {
            Text(
                text = stringResource(R.string.enter_for_weather_info),
                style = NimbusTheme.typography.labelLarge.copy(
                    color = NimbusTheme.colors.secondaryContentColor,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_32))
            )
        }
    )
}

@Composable
private fun BodyText(
    text: String
) {
    Text(
        text = text,
        style = NimbusTheme.typography.titleLarge,
        color = NimbusTheme.colors.secondaryContentColor
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