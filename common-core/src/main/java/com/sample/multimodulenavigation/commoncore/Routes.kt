package com.sample.multimodulenavigation.commoncore

sealed class Screen(val route: String) {
    object Splash : Screen("splash_root")
    object Auth : Screen("auth_root")
    object Dashboard : Screen("dashboard_root")
}

sealed class AuthScreen(val route: String) {
    object LogIn : AuthScreen("login")
    object LogInSuccess : AuthScreen("login_success")
}

sealed class DashboardScreen(val route: String) {
    object Gateway : DashboardScreen("load_tabs")
    object Home : DashboardScreen("home")
    object Tv : DashboardScreen("tv")
    object Movies : DashboardScreen("movies")
    object Sports : DashboardScreen("sports")

    object Result : DashboardScreen("result/{tabTitle}/{count}") {
        fun createRoute(tabTitle: String, count: Int) = "result/$tabTitle/$count"
    }
}