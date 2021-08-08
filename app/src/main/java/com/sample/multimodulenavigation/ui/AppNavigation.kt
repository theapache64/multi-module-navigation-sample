package com.sample.multimodulenavigation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sample.multimodulenavigation.auth.addAuthNavGraph
import com.sample.multimodulenavigation.commonutils.Screen
import com.sample.multimodulenavigation.dashboard.Tab
import com.sample.multimodulenavigation.dashboard.addDashboardNavGraph
import com.sample.multimodulenavigation.ui.screen.splash.SplashScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
    onTabsChanged: (List<Tab>) -> Unit,
    onTabsVisibilityChanged: (Boolean) -> Unit,
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
            navController = navController
        )

        addDashboardNavGraph(
            route = Screen.Dashboard.route,
            onTabsChanged = onTabsChanged,
            onTabsVisibilityChanged = onTabsVisibilityChanged,
            navController = navController
        )
    }
}
