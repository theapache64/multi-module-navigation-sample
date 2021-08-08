package com.sample.multimodulenavigation.dashboard

import androidx.compose.ui.graphics.vector.ImageVector
import com.sample.multimodulenavigation.common.LeafScreen

data class Tab(
    val id: String,
    val title: String,
    val icon: ImageVector
)


fun Tab.findScreen(): LeafScreen {
    return when (id) {
        LeafScreen.Home.route -> LeafScreen.Home
        LeafScreen.Tv.route -> LeafScreen.Tv
        LeafScreen.Movies.route -> LeafScreen.Movies
        LeafScreen.Sports.route -> LeafScreen.Sports
        else -> throw IllegalArgumentException("Not screen defined for the tab $this")
    }
}
