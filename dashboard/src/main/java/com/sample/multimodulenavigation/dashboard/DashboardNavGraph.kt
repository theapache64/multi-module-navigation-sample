package com.sample.multimodulenavigation.dashboard

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.sample.multimodulenavigation.commoncore.DashboardScreen
import com.sample.multimodulenavigation.dashboard.screen.*


fun NavGraphBuilder.addDashboardNavGraph(
    route: String,
    navController: NavController,
    onTabsLoaded: (List<Tab>) -> Unit,
    onTabsVisibilityChanged: (Boolean) -> Unit
) {

    val onShowTabsClicked = {
        onTabsVisibilityChanged(true)
    }
    val onHideTabsClicked = {
        onTabsVisibilityChanged(false)
    }

    val onResult: (String, Int) -> Unit = { tabTitle, count ->
        navController.navigate(DashboardScreen.Result.createRoute(tabTitle, count))
    }

    navigation(route = route, startDestination = DashboardScreen.Gateway.route) {
        composable(DashboardScreen.Gateway.route) {
            DashboardGatewayScreen(
                onTabsLoaded = { tabs ->
                    onTabsLoaded(tabs)

                    // Go to first tab
                    val leafScreen = tabs.first().findLeafScreen()
                    tabSet.add(leafScreen)
                    navController.navigate(leafScreen.route) {
                        // remove up to gateway since we've data loaded
                        popUpTo(DashboardScreen.Gateway.route) { inclusive = true }
                    }

                    onTabsVisibilityChanged(true)
                }
            )
        }
        composable(DashboardScreen.Home.route) {
            HomeScreen(
                onShowTabsClicked = onShowTabsClicked,
                onHideTabsClicked = onHideTabsClicked,
                onSubmit = onResult
            )
        }
        composable(DashboardScreen.Tv.route) {
            TvScreen(
                onShowTabsClicked = onShowTabsClicked,
                onHideTabsClicked = onHideTabsClicked,
                onSubmit = onResult
            )
        }
        composable(DashboardScreen.Movies.route) {
            MoviesScreen(
                onShowTabsClicked = onShowTabsClicked,
                onHideTabsClicked = onHideTabsClicked,
                onSubmit = onResult
            )
        }
        composable(DashboardScreen.Sports.route) {
            SportsScreen(
                onShowTabsClicked = onShowTabsClicked,
                onHideTabsClicked = onHideTabsClicked,
                onSubmit = onResult
            )
        }

        composable(
            route = DashboardScreen.Result.route,
            arguments = listOf(
                navArgument("count") {
                    type = NavType.IntType
                },
                navArgument("tabTitle") {
                    type = NavType.StringType
                },
            )
        ) {
            ResultScreen(
                count = it.arguments?.getInt("count") ?: -1,
                tabTitle = it.arguments?.getString("tabTitle") ?: "[ERROR]",
            )
        }
    }
}