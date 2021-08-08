package com.sample.multimodulenavigation.dashboard

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.sample.multimodulenavigation.common.LeafScreen
import com.sample.multimodulenavigation.dashboard.screen.*


fun NavGraphBuilder.addDashboardNavGraph(
    route: String,
    navController: NavController,
    onTabsChanged: (List<Tab>) -> Unit,
    onTabsVisibilityChanged: (Boolean) -> Unit
) {

    val onShowTabsClicked = {
        onTabsVisibilityChanged(true)
    }
    val onHideTabsClicked = {
        onTabsVisibilityChanged(false)
    }

    val onResult: (String, Int) -> Unit = { tabTitle, count ->
        navController.navigate(LeafScreen.Result.createRoute(tabTitle, count))
    }

    navigation(route = route, startDestination = LeafScreen.DashboardGateway.route) {
        composable(LeafScreen.DashboardGateway.route) {
            DashboardGatewayScreen(
                onTabsChanged = { tabs ->
                    onTabsChanged(tabs)
                    onTabsVisibilityChanged(true)
                }
            )
        }
        composable(LeafScreen.Home.route) {
            HomeScreen(
                onShowTabsClicked = onShowTabsClicked,
                onHideTabsClicked = onHideTabsClicked,
                onSubmit = onResult
            )
        }
        composable(LeafScreen.Tv.route) {
            TvScreen(
                onShowTabsClicked = onShowTabsClicked,
                onHideTabsClicked = onHideTabsClicked,
                onSubmit = onResult
            )
        }
        composable(LeafScreen.Movies.route) {
            MoviesScreen(
                onShowTabsClicked = onShowTabsClicked,
                onHideTabsClicked = onHideTabsClicked,
                onSubmit = onResult
            )
        }
        composable(LeafScreen.Sports.route) {
            SportsScreen(
                onShowTabsClicked = onShowTabsClicked,
                onHideTabsClicked = onHideTabsClicked,
                onSubmit = onResult
            )
        }

        composable(
            route = LeafScreen.Result.route,
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