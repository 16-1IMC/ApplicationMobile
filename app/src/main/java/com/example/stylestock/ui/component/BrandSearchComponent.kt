package com.example.stylestock.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.stylestock.modele.Adidas
import com.example.stylestock.modele.Brand
import com.example.stylestock.ui.theme.Jura
import com.example.stylestock.ui.theme.K2D

@Composable
fun BrandSearchComponent(brand: Brand) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(9.dp))
            .offset(x = 0.dp, y = 0.dp)
            .width(366.dp)
            .height(81.dp),
    ) {
        AsyncImage(
            model = brand.banner.path,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            contentDescription = "banner"
        )
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 18.dp, end = 16.dp)
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
            val textStyleBody = androidx.compose.ui.text.TextStyle(
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = Jura,
                fontWeight = FontWeight.Bold,
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
        }
    }
}

@Preview
@Composable
fun BrandSearchComponentPreview() {
    BrandSearchComponent(Adidas)
}