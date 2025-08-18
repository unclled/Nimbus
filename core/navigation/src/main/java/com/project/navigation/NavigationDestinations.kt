package com.project.navigation

interface NavigationDestinations {
    val route: String
}

object Loading : NavigationDestinations {
    override val route: String
        get() = "loadingScreen"
}

object Auth : NavigationDestinations {
    override val route: String
        get() = "authScreen"
}

object Weather : NavigationDestinations {
    override val route: String
        get() = "weatherScreen"
}

