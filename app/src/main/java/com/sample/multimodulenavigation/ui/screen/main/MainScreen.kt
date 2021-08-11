package com.sample.multimodulenavigation.ui.screen.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sample.multimodulenavigation.dashboard.DashboardBottomNavigation
import com.sample.multimodulenavigation.dashboard.Tab
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

    /*navController.enableOnBackPressed(false)
    BackHandler {
        onBackNavigation() // TODO:
    }*/

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
