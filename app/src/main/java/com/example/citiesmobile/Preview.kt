package com.example.citiesmobile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    WelcomeScreen(navController)
}

@Preview(showBackground = true)
@Composable
fun SecondPreview() {
    val navController = rememberNavController()
    SecondScreen(navController = navController, cities = cities)
}