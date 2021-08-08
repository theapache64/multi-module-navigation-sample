package com.sample.multimodulenavigation.dashboard.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun MoviesScreen(
    onShowTabsClicked: () -> Unit,
    onHideTabsClicked: () -> Unit,
    onSubmit: (String, Int) -> Unit,
) {
    Counter(
        title = "Movies",
        onShowTabsClicked = onShowTabsClicked,
        onHideTabsClicked = onHideTabsClicked,
        onSubmit = onSubmit,
        modifier = Modifier.fillMaxSize()
    )
}
