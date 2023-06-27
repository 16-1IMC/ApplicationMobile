package com.example.stylestock

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.stylestock.ui.component.Header
import com.example.stylestock.ui.component.NavBar
import com.example.stylestock.ui.component.TitleComponent
import com.example.stylestock.ui.page.BottomNavigation
import com.example.stylestock.ui.page.MyFollowScreen
import com.example.stylestock.ui.page.NavigationGraph
import com.example.stylestock.ui.theme.Jet
import com.example.stylestock.ui.theme.StyleStockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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
