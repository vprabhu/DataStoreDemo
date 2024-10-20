package com.vpdevs.datastoredemo.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vpdevs.datastoredemo.viewmodel.UserViewmodel

enum class LandingScreen {
    LoginScreen,
    HomeScreen
}

@Composable
fun LandingScreen(
    userViewmodel: UserViewmodel = viewModel()
) {

    val navController = rememberNavController()

    val loginItems by userViewmodel.loginTimes.collectAsState()
    val userName by userViewmodel.userName.collectAsState()
    userViewmodel.getUserName()

    Scaffold {
        NavHost(
            navController = navController,
            startDestination = LandingScreen.LoginScreen.name,
            modifier = Modifier.padding(it)
        ) {

            composable(route = LandingScreen.LoginScreen.name) {
                LoginScreen(
                    onLoginButtonClicked = { userName, password ->
                        if (userName == "tini" && password == "123") {
                            userViewmodel.saveUserName(userName)
                            userViewmodel.saveLoginItems()
                            navController.navigate(LandingScreen.HomeScreen.name)
                        }
                    }
                )
            }

            composable(route = LandingScreen.HomeScreen.name) {
                HomeScreen(
                    userName = userName,
                    loginTimes = loginItems
                ) {
                    navController.navigate(LandingScreen.LoginScreen.name) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }

            }

        }
    }

}
