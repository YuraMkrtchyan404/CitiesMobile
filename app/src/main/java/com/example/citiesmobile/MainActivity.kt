package com.example.citiesmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = WELCOME_SCREEN_ROUTE) {
                composable(WELCOME_SCREEN_ROUTE) { WelcomeScreen(navController) }
                composable(SECOND_SCREEN_ROUTE) { SecondScreen(navController, cities) }
            }
        }
    }
}
