package com.project.navigation

interface NavigationDestinations {
    val route: String
}

object Auth : NavigationDestinations {
    override val route: String
        get() = "authScreen"
}

object Weather : NavigationDestinations {
    override val route: String
        get() = "weatherScreen"
}

