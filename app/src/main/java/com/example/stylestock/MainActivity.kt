package com.example.stylestock

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.stylestock.ui.page.BottomNavigation
import com.example.stylestock.ui.page.NavigationGraph
import com.example.stylestock.ui.theme.Jet


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        StrictMode.setThreadPolicy(
            ThreadPolicy.Builder()
                .permitAll()
                .build()
        )
        super.onCreate(savedInstanceState)

        setContent {
            var showBottomBar by rememberSaveable { mutableStateOf(true) }
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            showBottomBar = when (navBackStackEntry?.destination?.route) {
                "login" -> false // on this screen bottom bar should be hidden
                "register"->false
                else -> true // in all other cases show bottom bar
            }
            Scaffold(
                bottomBar = { if (showBottomBar) BottomNavigation(navController = navController) }
            )
            {innerPadding ->
                NavigationGraph(navController = navController)
            }
        }
        statusbarcolor()
    }

    private fun statusbarcolor(){
        window.statusBarColor = Jet.toArgb()

    }
}
