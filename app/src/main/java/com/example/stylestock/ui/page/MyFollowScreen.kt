package com.example.stylestock.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.stylestock.ui.component.Header
import com.example.stylestock.ui.component.NavBar
import com.example.stylestock.ui.component.TitleComponent


@Composable
fun MyFollowScreen(navController: NavController) {
    Column {
        Header()


        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())) {
            TitleComponent(text = "My Follow")
        }
    }
}