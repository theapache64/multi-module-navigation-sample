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
import androidx.navigation.compose.rememberNavController
import com.sample.multimodulenavigation.dashboard.DashboardBottomNavigation
import com.sample.multimodulenavigation.dashboard.Tab
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
                    val currentRoute = navController.currentBackStackEntry?.destination?.route
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
