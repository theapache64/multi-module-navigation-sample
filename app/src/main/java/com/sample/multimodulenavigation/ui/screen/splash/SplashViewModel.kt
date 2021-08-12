package com.sample.multimodulenavigation.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.multimodulenavigation.BuildConfig
import com.sample.multimodulenavigation.navigation.NavigationManager
import com.sample.multimodulenavigation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    navigationManager: NavigationManager
) : ViewModel() {

    companion object {
        private const val SPLASH_DURATION_IN_MILLIS = 1500L
    }

    private val _versionName = MutableStateFlow("v${BuildConfig.VERSION_NAME}")
    val versionName = _versionName.asStateFlow()

    init {
        println("Manager is $navigationManager")
        viewModelScope.launch {
            delay(SPLASH_DURATION_IN_MILLIS)
            navigationManager.navigate(Screen.AuthRoot.route)
        }
    }
}