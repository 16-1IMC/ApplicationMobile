package com.example.stylestock.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.stylestock.modele.Brand
import com.example.stylestock.ui.theme.*


@Composable
fun NotificationComponent(brand: Brand) {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(NeonBlue)
            .width(330.dp)
            .height(54.dp)
            .clickable { System.out.println("Clicked") },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(50.dp))
                .background(Color.White)
                .height(46.dp)
                .width(46.dp),
            contentAlignment = Alignment.Center
        ) {

            AsyncImage(
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .background(Color.White)
                    .size(30.dp),
                model = brand.logo.path,
                contentDescription = "logo"
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Row(
            modifier = Modifier
                .padding(end = 30.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val textStyleBody = androidx.compose.ui.text.TextStyle(
                color = WhiteSmoke,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = K2D
            )
            var textStyle by remember { mutableStateOf(textStyleBody) }
            var readyToDraw by remember { mutableStateOf(false) }
            Text(
                text = brand.name,
                style = textStyle,
                softWrap = true,
                modifier = Modifier
                    .width(130.dp)
                    .drawWithContent {
                        if (readyToDraw) drawContent()
                    },
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.didOverflowHeight) {
                        textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9)
                    } else {
                        readyToDraw = true
                    }
                }
            )

            Text(
                text = "New Post",
                color = WhiteSmoke,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = K2D
            )
        }
    }
}
