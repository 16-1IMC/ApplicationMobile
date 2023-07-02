package com.example.stylestock.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stylestock.R


@Composable
fun BackArrowComponent(navController: NavController){
    IconButton(onClick = {
        navController.popBackStack()
    }) {
        Image(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "back",
            modifier = Modifier
                .padding(top = 21.dp)
                .size(35.dp)
        )
    }
}