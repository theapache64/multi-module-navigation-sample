package com.sample.multimodulenavigation.dashboard

import androidx.compose.ui.graphics.vector.ImageVector
import com.sample.multimodulenavigation.commoncore.DashboardScreen

data class Tab(
    val id: String,
    val title: String,
    val icon: ImageVector
)


fun Tab.findLeafScreen(): DashboardScreen {
    return when (id) {
        DashboardScreen.Home.route -> DashboardScreen.Home
        DashboardScreen.Tv.route -> DashboardScreen.Tv
        DashboardScreen.Movies.route -> DashboardScreen.Movies
        DashboardScreen.Sports.route -> DashboardScreen.Sports
        else -> throw IllegalArgumentException("Not screen defined for the tab $this")
    }
}
