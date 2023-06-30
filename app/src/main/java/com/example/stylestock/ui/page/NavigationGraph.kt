package com.example.stylestock.ui.page

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = "follow") {
            composable(BottomNavItem.Follow.screen_route) { MyFollowScreen(navController) }
            composable(BottomNavItem.Trend.screen_route) { TrendScreen(navController) }
            composable(BottomNavItem.Notification.screen_route) { NotificationScreen(navController) }
            composable(BottomNavItem.Profil.screen_route) { ProfilScreen(navController) }
            composable(BottomNavItem.NewBrand.screen_route) { NewBrandScreen(navController) }
            composable("searchBrand") { SearchBrandScreen(navController) }
            composable("searchPost") { SearchPostScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("register") { RegisterScreen(navController) }
        }
}