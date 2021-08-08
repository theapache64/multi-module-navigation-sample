package com.sample.multimodulenavigation.dashboard.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
