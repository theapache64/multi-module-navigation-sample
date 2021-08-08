package com.sample.multimodulenavigation.dashboard.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun SportsScreen(
    onShowTabsClicked: () -> Unit,
    onHideTabsClicked: () -> Unit,
    onSubmit: (String,Int) -> Unit,
) {
    Counter(
        title = "Sports",
        onShowTabsClicked = onShowTabsClicked,
        onHideTabsClicked = onHideTabsClicked,
        onSubmit = onSubmit,
        modifier = Modifier.fillMaxSize()
    )
}
