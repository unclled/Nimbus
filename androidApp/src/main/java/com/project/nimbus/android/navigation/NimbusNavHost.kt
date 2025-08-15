package com.project.nimbus.android.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.auth.presentation.AuthScreen
import com.project.navigation.Auth
import com.project.navigation.Weather
import com.project.weather.WeatherScreen

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun NimbusNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable(route = Auth.route) {
            AuthScreen()
        }

        composable(route = Weather.route) {
            WeatherScreen()
        }
    }
}
