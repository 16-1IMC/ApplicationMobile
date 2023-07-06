package com.example.stylestock.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.stylestock.modele.Brand


@Composable
fun LogoBrand(navController: NavController, brand: Brand,size: Dp){
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50.dp))
            .background(Color.White)
            .size(size+10.dp)
            .clickable { navController.navigate("brand/${brand.id}") },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .background(Color.White)
                .size(size),
            model = brand.profilePicture.toString(),
            contentDescription = "logo"
        )
    }
}