package com.sample.multimodulenavigation.ui.screen.main

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sample.multimodulenavigation.commoncore.DashboardScreen
import com.sample.multimodulenavigation.dashboard.DashboardBottomNavigation
import com.sample.multimodulenavigation.dashboard.Tab
import com.sample.multimodulenavigation.dashboard.findLeafScreen
import com.sample.multimodulenavigation.dashboard.tabSet
import com.sample.multimodulenavigation.ui.AppNavigation

const val TAG = "CustomLog"

@Composable
fun MainScreen(
    onBackNavigation: () -> Unit // temporary way
) {
    // States
    val navController = rememberNavController()
    var tabs by remember { mutableStateOf<List<Tab>>(listOf()) }
    var isTabsVisible by remember { mutableStateOf(false) }

    navController.enableOnBackPressed(false)
    BackHandler {
        if (!navController.popBackStackSmart(tabs.firstOrNull()?.findLeafScreen())) {
            onBackNavigation() // TODO: This is not the right way. Needs to improve
        }
    }

    Scaffold(
        bottomBar = {
            if (isTabsVisible && tabs.isNotEmpty()) {
                DashboardBottomNavigation(tabs = tabs, navController)
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AppNavigation(
                navController = navController,
                onTabsLoaded = { newTabs ->
                    tabs = newTabs
                },
                onTabsVisibilityChanged = { newIsTabsVisible ->
                    isTabsVisible = newIsTabsVisible
                }
            )
        }
    }
}

private fun NavHostController.popBackStackSmart(firstTab: DashboardScreen?): Boolean {
    if (firstTab == null || previousBackStackEntry == null) {
        return false
    }

    val currentRoute = currentBackStackEntry?.destination?.route
    val isTab = when (currentRoute) {
        DashboardScreen.Home.route,
        DashboardScreen.Tv.route,
        DashboardScreen.Movies.route,
        DashboardScreen.Sports.route -> true
        else -> false
    }
    return if (isTab) {
        var navBackTo = tabSet.lastOrNull()
        if (navBackTo?.route == currentRoute) {
            tabSet.remove(currentRoute!!)
            navBackTo = tabSet.lastOrNull()
        }
        if (navBackTo != null) {
            navigate(navBackTo.route) {
                popUpTo(firstTab.route) {
                    saveState = true
                }

                launchSingleTop = true
                restoreState = true
            }
            true
        } else {
            popBackStack()
        }
    } else {
        popBackStack()
    }
}
