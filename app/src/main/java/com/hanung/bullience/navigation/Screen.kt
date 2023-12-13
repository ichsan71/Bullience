package com.hanung.bullience.navigation

sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object Lapor : Screen("Lapor")
    object Jelajah : Screen("Jelajah")
    object Profile : Screen("Profile")

    object DetailLapor : Screen("home/{LaporId}") {
        fun createRoute(laporId: Long) = "home/$laporId"
    }

}