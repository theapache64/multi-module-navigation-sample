package com.sample.multimodulenavigation.dashboard

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun DashboardBottomNavigation(
    tabs: List<Tab>,
    navController: NavController
) {
    BottomNavigation {
        for (tab in tabs) {
            BottomNavigationItem(
                selected = false, // TODO:
                onClick = {
                    navController.navigate(tab.findScreen().route) {
                        popUpTo(tabs.first().findScreen().route) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(tab.title) },
                icon = { Icon(imageVector = tab.icon, contentDescription = "") }
            )
        }
    }
}
