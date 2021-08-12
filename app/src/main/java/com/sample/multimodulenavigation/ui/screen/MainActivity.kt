package com.sample.multimodulenavigation.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.sample.multimodulenavigation.navigation.NavigationManager
import com.sample.multimodulenavigation.ui.screen.main.MainScreen
import com.sample.multimodulenavigation.ui.screen.splash.SplashViewModel
import com.sample.multimodulenavigation.ui.theme.ComposeAndroidTemplateTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var navigationManager = NavigationManager()

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun splashViewModelFactory(): SplashViewModel.Factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAndroidTemplateTheme {
                Surface {
                    MainScreen(navigationManager)
                }
            }
        }
    }


}
