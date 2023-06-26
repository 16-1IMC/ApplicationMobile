package com.example.stylestock.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.stylestock.ui.component.Header
import com.example.stylestock.ui.component.TitleComponent

@Composable
fun NotificationScreen(navController: NavController){
    Column {
        Header(navController)
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())) {
            TitleComponent(text = "Notifications")
        }
    }
}