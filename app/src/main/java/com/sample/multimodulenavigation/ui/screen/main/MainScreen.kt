package com.sample.multimodulenavigation.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sample.multimodulenavigation.common.LeafScreen
import com.sample.multimodulenavigation.dashboard.DashboardBottomNavigation
import com.sample.multimodulenavigation.dashboard.Tab
import com.sample.multimodulenavigation.dashboard.findScreen
import com.sample.multimodulenavigation.ui.AppNavigation

@Composable
fun MainScreen() {
    // States
    val navController = rememberNavController()
    var tabs by remember { mutableStateOf<List<Tab>>(listOf()) }
    var isTabsVisible by remember { mutableStateOf(false) }

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
                onTabsChanged = { newTabs ->
                    tabs = newTabs

                    // Go to first tab
                    navController.navigate(newTabs.first().findScreen().route) {
                        // remove up to gateway since we've data loaded
                        popUpTo(LeafScreen.DashboardGateway.route) { inclusive = true }
                    }
                },
                onTabsVisibilityChanged = { newIsTabsVisible ->
                    isTabsVisible = newIsTabsVisible
                }
            )
        }
    }
}
