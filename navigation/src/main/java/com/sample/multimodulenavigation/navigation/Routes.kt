package com.sample.multimodulenavigation.navigation

sealed class Screen(val route: String) {
    object SplashRoot : Screen("splash_root")
    object AuthRoot : Screen("auth_root")
    object DashboardRoot : Screen("dashboard_root")
}

sealed class AuthScreen(val route: String) {
    object LogIn : AuthScreen("login")
    object LogInSuccess : AuthScreen("login_success")
}

sealed class DashboardScreen(val route: String) {
    object Counter : DashboardScreen("counter")

    object Result : DashboardScreen("result/{tabTitle}/{count}") {
        fun createRoute(tabTitle: String, count: Int) = "result/$tabTitle/$count"
    }

    object Page : DashboardScreen("page/{pageId}") {
        fun createRoute(pageId: String) = "page/$pageId"
    }
}