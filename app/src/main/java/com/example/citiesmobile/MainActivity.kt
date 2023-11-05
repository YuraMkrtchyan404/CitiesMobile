package com.example.citiesmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.res.painterResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "welcome_screen") {
                composable("welcome_screen") { WelcomeScreen(navController) }
                composable("second_screen") { SecondScreen(navController, cities) }
            }
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavController) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Welcome to the App!",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "We're glad to have you here.",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("second_screen") }) {
                    Text(text = "Go to Second Screen")
                }
            }
        }
    }
}

data class City(val name: String, val description: String, val imageRes: Int)

val cities = listOf(
    City(
        "Yerevan",
        "Yerevan, the capital of Armenia, is a city steeped in history and culture. Known" +
                " as the \"Pink City\" for its distinctive rose-hued tufa stone architecture, Yerevan" +
                " enchants visitors with its blend of ancient landmarks and modern vitality.",
        R.drawable.yerevan
    ),
    City(
        "Washington",
        "Washington D.C., the capital of the United States, stands as a symbol of democracy" +
                " and history. The city is lined with iconic neoclassical monuments and buildings," +
                " including the stately Capitol Building, the White House, and the Supreme Court.",
        R.drawable.washington
    ),
    City(
        "Madrid",
        "Madrid, Spain's central capital, is a city of elegant boulevards and expansive," +
                " manicured parks such as the Buen Retiro. It's renowned for its rich repositories of" +
                " European art, including the Prado Museum's works by Goya, Velázquez, and other" +
                " Spanish masters",
        R.drawable.madrid
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController, cities: List<City>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cities List") },
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(innerPadding)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn {
                    items(cities) { city ->
                        CityItem(city)
                    }
                }
            }
        }
    }
}

@Composable
fun CityItem(city: City) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = city.name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = city.description,
            style = MaterialTheme.typography.bodyMedium
        )
        Image(
            painter = painterResource(id = city.imageRes),
            contentDescription = "Image of ${city.name}",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
    }
}

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
