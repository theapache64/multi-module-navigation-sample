package com.sample.multimodulenavigation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val SCREEN_LOGIN = "login"
const val SCREEN_LOGIN_SUCCESS = "login_success"

fun NavGraphBuilder.addAuthNavGraph(
    route: String,
    navController: NavController,
    onGoToDashboardClicked: () -> Unit
) {
    navigation(
        route = route,
        startDestination = SCREEN_LOGIN
    ) {

        composable(SCREEN_LOGIN) {
            LogInScreen(
                onLogInSuccess = {
                    navController.navigate(SCREEN_LOGIN_SUCCESS)
                }
            )
        }

        composable(SCREEN_LOGIN_SUCCESS) {
            LogInSuccessScreen(onGoToDashboardClicked)
        }
    }
}

@Composable
fun LogInScreen(
    onLogInSuccess: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = onLogInSuccess
        ) {
            Text("LOGIN")
        }
    }
}

@Composable
fun LogInSuccessScreen(
    onGoToDashboardClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "LOGIN SUCCESS!")
        Button(
            onClick = onGoToDashboardClicked
        ) {
            Text("GO TO DASHBOARD")
        }
    }
}