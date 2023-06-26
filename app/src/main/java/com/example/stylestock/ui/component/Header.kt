package com.example.stylestock.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stylestock.R
import com.example.stylestock.ui.theme.NeonBlue
import com.example.stylestock.ui.theme.WhiteSmoke


@Composable
fun Header(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.logo_titre
        ),
            contentDescription = "logo",
            contentScale = ContentScale.Fit ,
            modifier = Modifier
                .padding(start = 13.dp)
                .width(150.dp)
        )
        IconButton(onClick = { navController.navigate("trend")},){
            Image(painter = painterResource(id = R.drawable.icon_search), contentDescription = "search",
                modifier = Modifier
                    .padding(top=10.dp, end = 20.dp)
                    .size(30.dp)
)
        }
    }
}
