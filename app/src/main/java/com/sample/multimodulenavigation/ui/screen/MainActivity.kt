package com.sample.multimodulenavigation.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.multimodulenavigation.auth.addAuthNavGraph
import com.sample.multimodulenavigation.dashboard.addDashboardNavGraph
import com.sample.multimodulenavigation.ui.screen.splash.SplashScreen
import com.sample.multimodulenavigation.ui.theme.ComposeAndroidTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        private const val ROUTE_SPLASH = "splash"
        private const val ROUTE_AUTH = "auth"
        private const val ROUTE_DASHBOARD = "dashboard"
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposeAndroidTemplateTheme {
                Surface {
                    NavHost(navController = navController, startDestination = ROUTE_SPLASH) {

                        // Splash
                        composable(ROUTE_SPLASH) {
                            SplashScreen(
                                onSplashFinished = {
                                    navController.popBackStack() // Remove splash from stack
                                    navController.navigate(ROUTE_AUTH)
                                }
                            )
                        }

                        addAuthNavGraph(
                            route = ROUTE_AUTH,
                            navController = navController,
                            onGoToDashboardClicked = {
                                navController.navigate(ROUTE_DASHBOARD)
                            }
                        )

                        addDashboardNavGraph(
                            route = ROUTE_DASHBOARD
                        )
                    }
                }
            }
        }
    }


}
