package com.example.stylestock.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.stylestock.modele.Adidas
import com.example.stylestock.modele.AdidasPost
import com.example.stylestock.modele.Brand
import com.example.stylestock.ui.component.BackArrowComponent
import com.example.stylestock.ui.component.LogoBrand
import com.example.stylestock.ui.component.PostComponent
import com.example.stylestock.ui.component.TitleComponent
import com.example.stylestock.ui.theme.Jura
import com.example.stylestock.ui.theme.K2D
import com.example.stylestock.ui.theme.NeonGreen
import com.example.stylestock.ui.theme.WhiteSmoke


@Composable
fun BrandScreen(navController: NavController, brandId: String?) {
    val brand = Adidas
    Box() {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(732.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillHeight,
                    model = brand.banner.path,
                    contentDescription = "banner"
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                )
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 19.dp)
                        ) {
                            val textStyleBody = androidx.compose.ui.text.TextStyle(
                                color = Color.White,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Jura
                            )
                            var textStyle by remember { mutableStateOf(textStyleBody) }
                            var readyToDraw by remember { mutableStateOf(false) }
                            LogoBrand(navController, brand, 66.dp)
                            Column(
                                modifier = Modifier.padding(start = 16.dp)
                            ) {
                                Text(
                                    text = brand.name,
                                    style = textStyle,
                                    softWrap = true,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .drawWithContent {
                                            if (readyToDraw) drawContent()
                                        },
                                    onTextLayout = { textLayoutResult ->
                                        if (textLayoutResult.didOverflowWidth) {
                                            textStyle =
                                                textStyle.copy(fontSize = textStyle.fontSize * 0.9)
                                        } else {
                                            readyToDraw = true
                                        }
                                    }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "1 Follow",
                                        fontSize = 16.sp,
                                        fontFamily = K2D,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    TextButton(
                                        modifier = Modifier
                                            .border(
                                                width = 1.dp,
                                                color = Color(0xFF65F543),
                                                shape = RoundedCornerShape(size = 5.dp)
                                            )
                                            .width(70.dp)
                                            .height(30.dp),
                                        onClick = { /*TODO*/ },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color(0x4265F543),
                                            contentColor = Color.White,

                                            ),
                                        contentPadding = PaddingValues(0.dp),
                                        content = {
                                            Text(
                                                text = "Follow",
                                                fontSize = 15.sp,
                                                fontFamily = K2D,
                                                color = Color(0xFFFFFFFF),
                                                textAlign = TextAlign.Center
                                            )
                                        },

                                        )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
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
            Spacer(modifier = Modifier.height(24.dp))
            TitleComponent(text = "Description", withBar = true)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = brand.description,
                color = Color.Black,
                fontFamily = K2D,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 33.dp, end = 33.dp)
            )
            TitleComponent(text = "Post", withBar = true)
            Column(
                modifier = Modifier
                    .padding(start = 33.dp)
            ) {

                for (i in 0..10) {
                    PostComponent(navController, AdidasPost)
                }
                Spacer(
                    modifier = Modifier
                        .height(70.dp)
                )
            }
        }
        BackArrowComponent(navController = navController)
    }
}