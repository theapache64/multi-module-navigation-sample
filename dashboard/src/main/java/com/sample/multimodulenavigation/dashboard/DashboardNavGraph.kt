package com.sample.multimodulenavigation.dashboard

import androidx.hilt.navigation.compose.hiltViewModel
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
    onBottomMenuLoaded: (List<Tab>) -> Unit,
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

    navigation(route = route, startDestination = DashboardScreen.Counter.route) {

        composable(DashboardScreen.Counter.route) {
            CounterScreen(
                title = "Counter",
                onShowTabsClicked = onShowTabsClicked,
                onHideTabsClicked = onHideTabsClicked,
                onSubmit = onResult
            )
        }

        composable(
            route = DashboardScreen.Page.route,
            arguments = listOf(
                navArgument("pageId") {
                    type = NavType.StringType
                }
            )
        ) {
            PageScreen(
                viewModel = hiltViewModel(it),
                onTabsLoaded = { tabs ->
                    onBottomMenuLoaded(tabs)
                    onTabsVisibilityChanged(true)
                }
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