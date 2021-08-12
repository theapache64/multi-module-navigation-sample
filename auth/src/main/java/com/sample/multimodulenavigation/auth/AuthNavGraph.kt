package com.sample.multimodulenavigation.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sample.multimodulenavigation.navigation.AuthScreen
import com.sample.multimodulenavigation.navigation.DashboardScreen
import com.sample.multimodulenavigation.navigation.Screen

fun NavGraphBuilder.addAuthNavGraph(
    route: String,
    navController: NavController
) {
    navigation(
        route = route,
        startDestination = AuthScreen.LogIn.route
    ) {

        // LogIn
        composable(
            route = AuthScreen.LogIn.route
        ) {
            LogInScreen(
                onLogInSuccess = {
                    navController.navigate(AuthScreen.LogInSuccess.route)
                }
            )
        }

        // LogInSuccess
        composable(AuthScreen.LogInSuccess.route) {
            LogInSuccessScreen(
                onGoToDashboardClicked = {
                    navController.navigate(DashboardScreen.Page.createRoute("page_0")) {
                        popUpTo(Screen.AuthRoot.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
