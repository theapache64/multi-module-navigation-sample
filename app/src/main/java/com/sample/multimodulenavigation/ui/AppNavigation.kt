package com.sample.multimodulenavigation.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sample.multimodulenavigation.auth.LogInScreen
import com.sample.multimodulenavigation.auth.LogInSuccessScreen
import com.sample.multimodulenavigation.dashboard.*
import com.sample.multimodulenavigation.ui.screen.splash.SplashScreen

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
) {

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        // Splash
        composable(Screen.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.popBackStack() // Remove splash from stack
                    navController.navigate(Screen.Auth.route)
                }
            )
        }

        addAuthNavGraph(
            route = Screen.Auth.route,
            navController = navController,
            onGoToDashboardClicked = {
                navController.navigate(Screen.Dashboard.route)
            }
        )

        addDashboardNavGraph(
            route = Screen.Dashboard.route
        )
    }
}

private fun NavGraphBuilder.addAuthNavGraph(
    route: String,
    navController: NavController,
    onGoToDashboardClicked: () -> Unit
) {
    navigation(
        route = route,
        startDestination = LeafScreen.LogIn.route
    ) {

        // LogIn
        composable(LeafScreen.LogIn.route) {
            LogInScreen(
                onLogInSuccess = {
                    navController.navigate(LeafScreen.LogInSuccess.route)
                }
            )
        }

        // LogInSuccess
        composable(LeafScreen.LogInSuccess.route) {
            LogInSuccessScreen(onGoToDashboardClicked)
        }
    }
}


fun NavGraphBuilder.addDashboardNavGraph(
    route: String
) {
    navigation(route = route, startDestination = LeafScreen.Home.route) {
        composable(LeafScreen.Home.route) {
            HomeScreen()
        }
        composable(LeafScreen.Tv.route) {
            TvScreen()
        }
        composable(LeafScreen.Movies.route) {
            MoviesScreen()
        }
        composable(LeafScreen.Sports.route) {
            SportsScreen()
        }
    }
}