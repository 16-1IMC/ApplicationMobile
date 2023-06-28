package com.example.stylestock.ui.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.stylestock.R

@Composable
fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = "follow") {
            composable(BottomNavItem.Follow.screen_route) { MyFollowScreen(navController) }
            composable(BottomNavItem.Trend.screen_route) { TrendScreen(navController) }
            composable(BottomNavItem.Notification.screen_route) { NotificationScreen(navController) }
            composable(BottomNavItem.Profil.screen_route) { ProfilScreen(navController) }
            composable(BottomNavItem.NewBrand.screen_route) { NewBrandScreen(navController) }
            composable("search") { SearchScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("register") { RegisterScreen(navController) }
        }
}