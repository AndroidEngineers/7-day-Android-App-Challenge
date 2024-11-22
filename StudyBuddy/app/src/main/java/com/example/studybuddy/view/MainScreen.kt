package com.example.studybuddy.view

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.studybuddy.R
import com.example.studybuddy.domain.model.BottomNavigationItem
import com.example.studybuddy.view.chat.ChatListScreen
import com.example.studybuddy.view.chat.ChatListViewModel
import com.example.studybuddy.view.dashboard.DashBoardScreenRoute
import com.example.studybuddy.view.videocall.HomeScreen
import com.example.studybuddy.view.videocall.HomeScreenRoute
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@com.ramcosta.composedestinations.annotation.Destination(
    deepLinks = [
        DeepLink(
            action = Intent.ACTION_VIEW,
            uriPattern = "study_buddy://main"
        )
    ]
)

@Composable
fun MainScreenRoute(
    navigator: DestinationsNavigator,
    initialSelectedIndex: Int = 0 // Default to 0 (Dashboard)
) {
    MainScreen(
        modifier = Modifier,
        navigator = navigator,
        initialSelectedIndex = initialSelectedIndex
    )
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    initialSelectedIndex: Int
) {
    val navigationItems = listOf(
        BottomNavigationItem(
            title = "Dashboard",
            selectedIcon = R.drawable.homefilled,
            unselectedIcon = R.drawable.homeoutlined,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Chats",
            selectedIcon = R.drawable.chatfilled,
            unselectedIcon = R.drawable.chatoutlined,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Calls",
            selectedIcon = R.drawable.videocallfill,
            unselectedIcon = R.drawable.videocalloutline,
            hasNews = false
        ),
//        BottomNavigationItem(
//            title = "Focus",
//            selectedIcon = R.drawable.focusfill,
//            unselectedIcon = R.drawable.focus,
//            hasNews = false
//        )
    )

    var selectedIndex by remember { mutableStateOf(initialSelectedIndex) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = navigationItems,
                selectedIndex = selectedIndex,
                onItemSelected = { selectedIndex = it }
            )
        }
    ) { paddingValues ->
        ContentScreen(
            navigator = navigator,
            modifier = Modifier.padding(paddingValues),
            selectedIndex = selectedIndex
        )
    }
}


@Composable
fun BottomNavigationBar(items: List<BottomNavigationItem>, selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                icon = {
                    Icon(
                        painter = painterResource(id = if (selectedIndex == index) item.selectedIcon else item.unselectedIcon),
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                alwaysShowLabel = true // Show label always or only when selected
            )
        }
    }
}

@Composable
fun ContentScreen(navigator: DestinationsNavigator,modifier: Modifier = Modifier,selectedIndex: Int)
{
    when (selectedIndex) {
        0 -> DashBoardScreenRoute(navigator)
        1 -> ChatListScreen(navigator)
        2 -> HomeScreenRoute(navigator)
//        3 -> Text("Doubts Screen", modifier = modifier.fillMaxSize())
    }
}