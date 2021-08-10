package com.sample.multimodulenavigation.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sample.multimodulenavigation.commoncore.AuthScreen
import com.sample.multimodulenavigation.commoncore.Screen

fun NavGraphBuilder.addAuthNavGraph(
    route: String,
    navController: NavController
) {
    navigation(
        route = route,
        startDestination = AuthScreen.LogIn.route
    ) {

        // LogIn
        composable(AuthScreen.LogIn.route) {
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
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
