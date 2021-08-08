package com.sample.multimodulenavigation.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sample.multimodulenavigation.commoncore.LeafScreen
import com.sample.multimodulenavigation.dashboard.DashboardBottomNavigation
import com.sample.multimodulenavigation.dashboard.Tab
import com.sample.multimodulenavigation.dashboard.findLeafScreen
import com.sample.multimodulenavigation.dashboard.tabSet
import com.sample.multimodulenavigation.ui.AppNavigation

@Composable
fun MainScreen() {
    // States
    val navController = rememberNavController()
    var tabs by remember { mutableStateOf<List<Tab>>(listOf()) }
    var isTabsVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (isTabsVisible) {
                IconButton(onClick = {
                    navController.popBackStackSmart(tabs.first().findLeafScreen())
                }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack, contentDescription = null
                    )
                }
            }
        },
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

private fun NavHostController.popBackStackSmart(firstTab: LeafScreen) {
    val currentRoute = currentBackStackEntry?.destination?.route
    val isTab = when (currentRoute) {
        LeafScreen.Home.route,
        LeafScreen.Tv.route,
        LeafScreen.Movies.route,
        LeafScreen.Sports.route -> true
        else -> false
    }
    if (isTab) {
        tabSet.remove(currentRoute!!)
        val navBackTo = tabSet.lastOrNull()
        if (navBackTo != null) {
            navigate(navBackTo.route) {
                popUpTo(firstTab.route) {
                    saveState = true
                }

                launchSingleTop = true
                restoreState = true
            }
        }
    } else {
        popBackStack()
    }
}
