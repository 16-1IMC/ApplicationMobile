package com.example.stylestock.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.stylestock.modele.Brand
import com.example.stylestock.repository.BrandRepository
import com.example.stylestock.repository.UserStore
import com.example.stylestock.ui.theme.K2D
import com.example.stylestock.ui.theme.NeonGreen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun BrandComponent(navController: NavController, brand: Brand) {
    var context = LocalContext.current
    Spacer(modifier = Modifier.height(15.dp))
    Box(
        modifier = Modifier
            .width(345.dp)
            .height(586.dp)
            .clip(shape = RoundedCornerShape(33.dp))
            .clickable {
                runBlocking {
                    val res = BrandRepository(UserStore(context).getAccessToken.first() ).getBrandById(brand.id.toString())
                    if (res != null) {
                        navController.navigate("brand/${brand.id}") }
                    }
                }

    ) {
        AsyncImage(
            model = brand.banner.toString(),
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Inside,
            contentDescription = "banner"
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            LogoBrand(navController, brand, 66.dp)
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .padding(0.dp)
                    .width(291.dp)
                    .height(2.dp)
                    .background(NeonGreen)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                brand.categories.forEach {
                    Text(
                        text = it.name,
                        fontSize = 16.sp,
                        fontFamily = K2D,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}