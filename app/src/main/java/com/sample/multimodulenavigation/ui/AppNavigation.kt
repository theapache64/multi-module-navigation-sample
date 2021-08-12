package com.sample.multimodulenavigation.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sample.multimodulenavigation.auth.addAuthNavGraph
import com.sample.multimodulenavigation.dashboard.Tab
import com.sample.multimodulenavigation.dashboard.addDashboardNavGraph
import com.sample.multimodulenavigation.navigation.NavigationManager
import com.sample.multimodulenavigation.navigation.Screen
import com.sample.multimodulenavigation.ui.screen.splash.SplashScreen
import com.sample.multimodulenavigation.ui.screen.splash.SplashViewModel
import com.sample.multimodulenavigation.ui.screen.splash.splashViewModel


@Composable
fun AppNavigation(
    navController: NavHostController,
    navigationManager: NavigationManager,
    onTabsLoaded: (List<Tab>) -> Unit,
    onTabsVisibilityChanged: (Boolean) -> Unit,
) {

    NavHost(navController = navController, startDestination = Screen.SplashRoot.route) {

        // Splash
        composable(Screen.SplashRoot.route) {
            val viewModel = splashViewModel(navigationManager = navigationManager)
            SplashScreen(
                viewModel
                /*onSplashFinished = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }*/
            )
        }

        addAuthNavGraph(
            route = Screen.AuthRoot.route,
            navController = navController
        )

        addDashboardNavGraph(
            route = Screen.DashboardRoot.route,
            onBottomMenuLoaded = onTabsLoaded,
            onTabsVisibilityChanged = onTabsVisibilityChanged,
            navController = navController
        )
    }
}
