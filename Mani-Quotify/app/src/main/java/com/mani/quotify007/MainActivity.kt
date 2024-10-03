package com.mani.quotify007

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mani.quotify007.ui.theme.QuotifyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotifyAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomAppBar(navController) }
                ) { innerPadding ->
                    NavigationGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuotifyAppTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomAppBar(navController) }
        ) { innerPadding ->
            NavigationGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun BottomAppBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Favorites
    )
    androidx.compose.material3.BottomAppBar {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = BottomNavItem.Home.route, modifier = modifier) {
        composable(BottomNavItem.Home.route) { HomeScreen() }
        composable(BottomNavItem.Search.route) { SearchScreen() }
        composable(BottomNavItem.Favorites.route) { FavoritesScreen() }
    }
}

sealed class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {
    object Home : BottomNavItem("Home", Icons.Default.Home, "home")
    object Search : BottomNavItem("Search", Icons.Default.Search, "search")
    object Favorites : BottomNavItem("Favorites", Icons.Default.Favorite, "favorites")
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quote of the Day", fontStyle = FontStyle.Italic, fontSize = 30.sp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(
                    //GradientStart: “#D9FF43”, GradientCenter: “#F67831”, GradientEnd: “#FF1493”
                    //generateRandomGradient()
                    brush = Brush.verticalGradient(
                        0.0f to Color.Blue, 1.0f to Color.White, startY = 0.0f, endY = 1500.0f
                    )
                )
        ) {
            // Card content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Success is the sum of small efforts, repeated day in and day out.",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "- Robert Collier",
                    fontFamily = FontFamily.Monospace,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}

@Composable
fun SearchScreen() {
    Text(text = "Search Screen")
}

@Composable
fun FavoritesScreen() {
    Text(text = "Favorites Screen")
}