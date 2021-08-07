package com.sample.multimodulenavigation.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.sample.multimodulenavigation.dashboard.Tab
import com.sample.multimodulenavigation.ui.AppNavigation
import com.sample.multimodulenavigation.ui.LeafScreen
import com.sample.multimodulenavigation.ui.Screen
import com.sample.multimodulenavigation.util.Resource

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    // States
    val navController = rememberNavController()
    val tabs by viewModel.tabs.collectAsState()
    val shouldShowTabs by viewModel.shouldShowTabs.collectAsState()

    // UI
    Scaffold(
        bottomBar = {
            if (shouldShowTabs && tabs is Resource.Success<List<Tab>>) {
                MainBottomNavigation((tabs as Resource.Success<List<Tab>>).data,
                    onTabClicked = { tab ->
                        navController.navigate(tab.findScreen().route) {
                            launchSingleTop = true
                            restoreState = true

                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                    }
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AppNavigation(navController = navController)
        }
    }
}

fun Tab.findScreen(): Screen {
    return when (id) {
        LeafScreen.Home.route -> LeafScreen.Home
        LeafScreen.Tv.route -> LeafScreen.Tv
        LeafScreen.Movies.route -> LeafScreen.Movies
        LeafScreen.Sports.route -> LeafScreen.Sports
        else -> throw IllegalArgumentException("Not screen defined for the tab $this")
    }
}

@Composable
fun MainBottomNavigation(
    tabs: List<Tab>,
    onTabClicked: (Tab) -> Unit
) {
    BottomNavigation {
        for (tab in tabs) {
            BottomNavigationItem(
                selected = false, // TODO:
                onClick = { onTabClicked(tab) },
                icon = { Icon(imageVector = tab.icon, contentDescription = "") }
            )
        }
    }
}
