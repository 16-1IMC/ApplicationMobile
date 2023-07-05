package com.example.stylestock

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.stylestock.repository.BrandRepository
import com.example.stylestock.repository.UserRepository
import com.example.stylestock.repository.UserStore
import com.example.stylestock.ui.page.BottomNavigation
import com.example.stylestock.ui.page.NavigationGraph
import com.example.stylestock.ui.theme.Jet
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        StrictMode.setThreadPolicy(
            ThreadPolicy.Builder()
                .permitAll()
                .build()
        )
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val apiKey = runBlocking { UserStore(context).getAccessToken.first() }
            var start = "login"
            if (apiKey != "") {
                Log.d("styleStock", apiKey)
                val user = runBlocking {
                    if (UserStore(context).getIsBrand.first()) {
                        BrandRepository(apiKey).getBrandById(UserStore(context).getUserId.first())
                    } else {
                        UserRepository(apiKey).getUserById(UserStore(context).getUserId.first())
                    }

                }
                if (user != "") {
                    start = "follow"
                } else {
                    start = "login"
                }
            }
            var showBottomBar by rememberSaveable { mutableStateOf(true) }
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            showBottomBar = when (navBackStackEntry?.destination?.route) {
                "login" -> false // on this screen bottom bar should be hidden
                "register" -> false
                else -> true // in all other cases show bottom bar
            }
            Scaffold(
                bottomBar = { if (showBottomBar) BottomNavigation(navController = navController) }
            )
            { innerPadding ->
                NavigationGraph(navController = navController,start)
            }
        }
        statusbarcolor()
    }

    private fun statusbarcolor() {
        window.statusBarColor = Jet.toArgb()

    }
}
