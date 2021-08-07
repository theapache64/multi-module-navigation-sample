package com.sample.multimodulenavigation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController

sealed class Screen(val route: String) {
    object Splash : Screen("splash_root")
    object Auth : Screen("auth_root")
    object Dashboard : Screen("dashboard_root")
}

sealed class LeafScreen(val route: String) {
    object LogIn : LeafScreen("login")
    object LogInSuccess : LeafScreen("login_success")

    object Home : Screen("home")
    object Tv : Screen("tv")
    object Movies : Screen("movies")
    object Sports : Screen("sports")
}

@Composable
fun AppNavigation(
    navController: NavHostController
){

}