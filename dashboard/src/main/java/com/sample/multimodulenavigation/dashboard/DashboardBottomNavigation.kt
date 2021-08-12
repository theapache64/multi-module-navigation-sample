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

                    /*if (tab.pageId != null) {
                        // should load dynamic page
                        val route = DashboardScreen.Page.createRoute(tab.pageId)
                        navController.navigate(route)
                    } else {
                        // should load static page
                        navController.navigate(DashboardScreen.Counter.route)
                    }

                    tabSet.bringToFront(tab.id)*/
                },
                label = { Text(tab.title) },
                icon = { Icon(imageVector = tab.icon, contentDescription = "") }
            )
        }
    }
}
