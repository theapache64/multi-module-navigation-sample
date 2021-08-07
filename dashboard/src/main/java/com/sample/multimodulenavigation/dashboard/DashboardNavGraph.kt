package com.sample.multimodulenavigation.dashboard

import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.addDashboardNavGraph(
    route: String
) {
    navigation(route = route, startDestination = "sample") {

        composable("sample") {
            Text("Sample Dashboard")
        }

    }
}