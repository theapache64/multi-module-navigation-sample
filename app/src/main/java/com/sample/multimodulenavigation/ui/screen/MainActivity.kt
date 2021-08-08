package com.sample.multimodulenavigation.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.sample.multimodulenavigation.ui.screen.main.MainScreen
import com.sample.multimodulenavigation.ui.theme.ComposeAndroidTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAndroidTemplateTheme {
                Surface {
                    MainScreen(
                        onBackNavigation = {
                            finish()
                        }
                    )
                }
            }
        }
    }


}
