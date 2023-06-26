package com.example.stylestock.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stylestock.R
import com.example.stylestock.ui.page.BottomNavItem
import com.example.stylestock.ui.theme.Jet
import com.example.stylestock.ui.theme.NeonBlue


@Composable
fun NavBar(navController: NavController, iconToFilled: String) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
            .background(Jet)
            .height(67.dp),
    ) {

    }
    Row(


    ) {
        IconButton(onClick = { navController.navigate("trend")},){
            Image(painter = painterResource(id = if (iconToFilled == "trend") BottomNavItem.Trend.icon_fill else BottomNavItem.Trend.icon_empty ), contentDescription = "trend",
                modifier = Modifier
                    .padding(top = 10.dp, end = 20.dp)
                    .size(33.dp)
            )
        }
        IconButton(onClick = { navController.navigate("new")},){
            Image(painter = painterResource(id = if (iconToFilled == "newbrand") BottomNavItem.NewBrand.icon_fill else BottomNavItem.NewBrand.icon_empty ), contentDescription = "newbrand",
                modifier = Modifier
                    .padding(top = 10.dp, end = 20.dp)
                    .size(33.dp)
            )
        }
        IconButton(onClick = { navController.navigate("follow")},){
            Image(painter = painterResource(id = if (iconToFilled == "myfollow") BottomNavItem.Follow.icon_fill else BottomNavItem.Follow.icon_empty ), contentDescription = "myfollow",
                modifier = Modifier
                    .padding(top = 10.dp, end = 20.dp)
                    .size(33.dp)
            )
        }
        IconButton(onClick = { navController.navigate("notification")},){
            Image(painter = painterResource(id = if (iconToFilled == "notification") BottomNavItem.Notification.icon_fill else BottomNavItem.Notification.icon_empty ), contentDescription = "notification",
                modifier = Modifier
                    .padding(top = 10.dp, end = 20.dp)
                    .size(33.dp)
            )
        }
        IconButton(onClick = { navController.navigate("profil")},){
            Image(painter = painterResource(id = if (iconToFilled == "profil") BottomNavItem.Profil.icon_fill else BottomNavItem.Profil.icon_empty ), contentDescription = "profil",
                modifier = Modifier
                    .padding(top = 10.dp, end = 20.dp)
                    .size(33.dp)
            )
        }

    }
}


