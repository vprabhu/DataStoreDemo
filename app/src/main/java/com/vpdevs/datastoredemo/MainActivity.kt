package com.vpdevs.datastoredemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vpdevs.datastoredemo.ui.screens.LandingScreen
import com.vpdevs.datastoredemo.ui.theme.DataStoreDemoTheme

data class UserPreferences(val showCompleted: Boolean)

private const val USER_PREFERENCES_NAME = "user_preferences"



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataStoreDemoTheme {
               LandingScreen()
            }
        }
    }
}
