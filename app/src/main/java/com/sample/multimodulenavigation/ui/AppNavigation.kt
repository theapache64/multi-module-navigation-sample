package com.sample.multimodulenavigation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sample.multimodulenavigation.auth.addAuthNavGraph
import com.sample.multimodulenavigation.commoncore.Screen
import com.sample.multimodulenavigation.dashboard.Tab
import com.sample.multimodulenavigation.dashboard.addDashboardNavGraph
import com.sample.multimodulenavigation.ui.screen.splash.SplashScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
    onTabsLoaded: (List<Tab>) -> Unit,
    onTabsVisibilityChanged: (Boolean) -> Unit,
) {

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        // Splash
        composable(Screen.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        addAuthNavGraph(
            route = Screen.Auth.route,
            navController = navController
        )

        addDashboardNavGraph(
            route = Screen.Dashboard.route,
            onTabsLoaded = onTabsLoaded,
            onTabsVisibilityChanged = onTabsVisibilityChanged,
            navController = navController
        )
    }
}
