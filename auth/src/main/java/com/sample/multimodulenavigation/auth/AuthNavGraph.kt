package com.sample.multimodulenavigation.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sample.multimodulenavigation.common.LeafScreen
import com.sample.multimodulenavigation.common.Screen

fun NavGraphBuilder.addAuthNavGraph(
    route: String,
    navController: NavController
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
