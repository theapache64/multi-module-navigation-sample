package com.sample.multimodulenavigation.dashboard.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.multimodulenavigation.commonutils.LeafScreen
import com.sample.multimodulenavigation.commonutils.Resource
import com.sample.multimodulenavigation.dashboard.Tab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun DashboardGatewayScreen(
    viewModel: DashboardGatewayViewModel = hiltViewModel(),
    onTabsChanged: (List<Tab>) -> Unit
) {
    val tabs by viewModel.tabs.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (tabs) {
            is Resource.Idle -> {
                Text(text = "Gateway is idle")
            }
            is Resource.Loading -> {
                GatewayLoading()
            }
            is Resource.Success -> {
                onTabsChanged((tabs as Resource.Success<List<Tab>>).data)
            }
            is Resource.Error -> {
                Text(text = "Something went wrong")
            }
        }
    }
}

@Composable
private fun GatewayLoading() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Loading dashboard", fontSize = 20.sp)
    }
}

@HiltViewModel
class DashboardGatewayViewModel @Inject constructor() : ViewModel() {
    private val _tabs = MutableStateFlow<Resource<List<Tab>>>(Resource.Idle())
    val tabs = _tabs.asStateFlow()

    init {
        loadTabs()
    }

    private fun loadTabs() {
        viewModelScope.launch {
            // Fake network call
            _tabs.value = Resource.Loading()
            delay(2000)
            _tabs.value = Resource.Success(
                listOf(
                    Tab(LeafScreen.Home.route, "Home", Icons.Outlined.Home),
                    Tab(LeafScreen.Tv.route, "Tv", Icons.Outlined.PlayArrow),
                    Tab(LeafScreen.Movies.route, "Movies", Icons.Outlined.Star),
                    Tab(LeafScreen.Sports.route, "Sports", Icons.Outlined.ThumbUp),
                )
            )
        }
    }
}