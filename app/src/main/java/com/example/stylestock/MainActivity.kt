package com.example.stylestock

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.stylestock.ui.component.Header
import com.example.stylestock.ui.component.NavBar
import com.example.stylestock.ui.component.TitleComponent
import com.example.stylestock.ui.page.BottomNavigation
import com.example.stylestock.ui.page.MyFollowScreen
import com.example.stylestock.ui.page.NavigationGraph
import com.example.stylestock.ui.theme.StyleStockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { BottomNavigation(navController = navController)}
            )
            {
                NavigationGraph(navController = navController)
            }
        }
    }
}
