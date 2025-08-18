package com.project.nimbus.android.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.auth.presentation.loading.AuthScreen
import com.project.auth.presentation.loading.LoadingScreen
import com.project.navigation.Loading
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
        composable(route = Loading.route) {
            LoadingScreen(
                navigateToAuth = {
                    navController.navigate("auth") {
                        popUpTo("auth") { // Чтобы пользователь не мог вернуться кнопкой "назад"
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable("auth") {
            AuthScreen()
        }

        composable(route = Weather.route) {
            WeatherScreen()
        }
    }
}
