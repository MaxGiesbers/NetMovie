package com.net.movie

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.net.movie.Home.HomeScreen
import com.net.movie.Home.MovieScreen
import com.net.movie.Home.NewMovies
import com.net.movie.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }

                MainNavigation()
            }
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val route: String
)


@Composable
fun MainNavigation() {
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
            route = "home"
        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = false,
            badgeCount = 45,
            route = "newMovies"
        ),
        BottomNavigationItem(
            title = "Account",
            selectedIcon = Icons.Filled.AccountBox,
            unselectedIcon = Icons.Outlined.AccountBox,
            hasNews = true,
            route = "newMovies"
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val navController = rememberNavController()

    // get navBackStackEntry as State so we can refresh the ui onBackStack event.
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    // get selected route name and show on SmallTopBar.
    // parent route name means nested graph route name.
    val parentRouteName = navBackStackEntry.value?.destination?.parent?.route

    //Get current page name from backStackEntry
    val routeName = navBackStackEntry.value?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->

                    NavigationBarItem(selected = parentRouteName == item.route,
                        onClick = {

                            selectedItemIndex = index

                            navController.navigate(item.route,navOptions {
                                // avoid building up a large stack of destinations
                                //on the back stack as users select items.
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }

                                // Avoid multiple copies fo the same destination when
                                // reselecting the same item.
                                launchSingleTop = true
                                restoreState = true
                            })
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if (item.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (parentRouteName == item.route) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }

                        })
                }
            }

        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "home", Modifier.padding(innerPadding)) {

            navigation(startDestination = "homepage", route = "home") {
                composable("homepage") {
                    HomeScreen(navController)
                }
                composable("movie/{movieId}") {
                    MovieScreen(navHostController = navController)
                }
            }

            navigation(startDestination = "newMoviesPage", route = "newMovies") {

                composable("newMoviesPage") {
                    NewMovies(navController)
                }
            }
        }
    }
}


