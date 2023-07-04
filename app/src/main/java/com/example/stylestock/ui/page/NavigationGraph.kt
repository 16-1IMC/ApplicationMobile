package com.example.stylestock.ui.page

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stylestock.modele.ApiKey
import com.example.stylestock.repository.ApiRepository
import com.example.stylestock.repository.UserRepository
import com.example.stylestock.repository.UserStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun NavigationGraph(navController: NavHostController) {
    val context = LocalContext.current
    val apiKey = runBlocking { UserStore(context).getAccessToken.first() }
    var start = "login"
    if (apiKey != "") {
        Log.d("styleStock", apiKey)
        val user = runBlocking { UserRepository(apiKey).getUserById(UserStore(context).getUserId.first()) }
        if (user != "") {
            Log.d("styleStock", "test")
            start = "follow"
        }
    }

    NavHost(navController, startDestination = start) {
        composable(BottomNavItem.Follow.screen_route) { MyFollowScreen(navController) }
        composable(BottomNavItem.Trend.screen_route) { TrendScreen(navController) }
        composable(BottomNavItem.Notification.screen_route) { NotificationScreen(navController) }
        composable(BottomNavItem.Profil.screen_route) { ProfilScreen(navController) }
        composable(BottomNavItem.NewBrand.screen_route) { NewBrandScreen(navController) }
        composable("searchBrand") { SearchBrandScreen(navController) }
        composable("searchPost") { SearchPostScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("brand/{brandId}") { backStackEntry ->
            BrandScreen(
                navController,
                backStackEntry.arguments?.getString("brandId")
            )
        }
        composable("post/{postId}") { backStackEntry ->
            PostScreen(
                navController,
                backStackEntry.arguments?.getString("postId")
            )
        }
    }
}