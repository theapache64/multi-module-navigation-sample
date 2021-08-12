package com.sample.multimodulenavigation.ui.screen.splash

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sample.multimodulenavigation.BuildConfig
import com.sample.multimodulenavigation.navigation.NavigationManager
import com.sample.multimodulenavigation.navigation.Screen
import com.sample.multimodulenavigation.ui.screen.MainActivity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Composable
fun splashViewModel(navigationManager: NavigationManager): SplashViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).splashViewModelFactory()

    return viewModel(factory = SplashViewModel.provideFactory(factory, navigationManager))
}

class SplashViewModel @AssistedInject constructor(
    @Assisted navigationManager: NavigationManager
) : ViewModel() {

    @dagger.assisted.AssistedFactory
    interface Factory {
        fun create(navigationManager: NavigationManager): SplashViewModel
    }


    companion object {
        private const val SPLASH_DURATION_IN_MILLIS = 1500L
        fun provideFactory(
            factory: Factory,
            navigationManager: NavigationManager
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return factory.create(navigationManager) as T
            }

        }
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