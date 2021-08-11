package com.sample.multimodulenavigation.dashboard

import androidx.compose.ui.graphics.vector.ImageVector

data class Tab(
    val id: String,
    val pageId: String?,
    val title: String,
    val icon: ImageVector
)