package com.project.nimbus.android.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun NimbusNavScreen(
    startDestination: String
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route ?: startDestination

    Scaffold(
        containerColor = Color.Transparent,
        snackbarHost = {},
        bottomBar = {}
    ) { _ ->
        NimbusNavHost(
            navController = navController,
            startDestination = startDestination
        )
    }
}