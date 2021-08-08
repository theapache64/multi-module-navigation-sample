package com.sample.multimodulenavigation.dashboard

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.sample.multimodulenavigation.commoncore.TabSet

val tabSet = TabSet()

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
                    val nextTab = tab.findScreen()
                    tabSet.add(nextTab)
                    navController.navigate(nextTab.route) {
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
