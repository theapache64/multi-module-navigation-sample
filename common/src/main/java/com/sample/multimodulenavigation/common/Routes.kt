package com.sample.multimodulenavigation.common

sealed class Screen(val route: String) {
    object Splash : Screen("splash_root")
    object Auth : Screen("auth_root")
    object Dashboard : Screen("dashboard_root")
}

sealed class LeafScreen(val route: String) {
    object LogIn : LeafScreen("login")
    object LogInSuccess : LeafScreen("login_success")

    object DashboardGateway : LeafScreen("load_tabs")
    object Home : LeafScreen("home")
    object Tv : LeafScreen("tv")
    object Movies : LeafScreen("movies")
    object Sports : LeafScreen("sports")

    object Result : LeafScreen("result/{tabTitle}/{count}") {
        fun createRoute(tabTitle: String, count: Int) = "result/$tabTitle/$count"
    }
}