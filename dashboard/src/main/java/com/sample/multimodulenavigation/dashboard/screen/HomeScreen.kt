package com.sample.multimodulenavigation.dashboard.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.sample.multimodulenavigation.commonutils.LeafScreen

@Composable
fun HomeScreen(
    onShowTabsClicked: () -> Unit,
    onHideTabsClicked: () -> Unit,
    onSubmit: (String, Int) -> Unit
) {
    Counter(
        "Home",
        onShowTabsClicked = onShowTabsClicked,
        onHideTabsClicked = onHideTabsClicked,
        onSubmit = onSubmit,
        modifier = Modifier.fillMaxSize()
    )
}
