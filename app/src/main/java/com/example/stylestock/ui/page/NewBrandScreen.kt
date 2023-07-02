package com.example.stylestock.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stylestock.modele.Adidas
import com.example.stylestock.ui.component.BrandComponent
import com.example.stylestock.ui.component.Header
import com.example.stylestock.ui.component.NavBar
import com.example.stylestock.ui.component.TitleComponent

@Composable
fun NewBrandScreen(navController: NavController){
    Column {
        Header(navController)
            TitleComponent(text = "New Brand", true)
        Column(modifier = Modifier
            .padding(start = 33.dp)
            .verticalScroll(rememberScrollState())) {
            BrandComponent(navController, Adidas)
        }
        Spacer(modifier = Modifier
            .height(70.dp))
    }
}