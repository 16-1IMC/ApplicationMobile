package com.example.stylestock.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.stylestock.modele.Brand
import com.example.stylestock.modele.Image
import com.example.stylestock.modele.Post
import com.example.stylestock.ui.theme.*
import java.time.format.TextStyle

@Composable
fun PostComponent(navController: NavController, post: Post) {

    Spacer(
        modifier = Modifier
            .height(15.dp)

    )
    Box(
        modifier = Modifier
            .height(233.dp)
            .width(345.dp)
            .clip(shape = RoundedCornerShape(28.dp))
            .background(NeonBlue)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(345.dp),
            contentScale = ContentScale.FillBounds,
            model = post.images[0].path,
            contentDescription = "background"
        )
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                LogoBrand(navController, post.brand, 36.dp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                val textStyleBody = androidx.compose.ui.text.TextStyle(
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = Jura,
                    fontWeight = FontWeight.Bold,
                )
                var textStyle by remember { mutableStateOf(textStyleBody) }
                var readyToDraw by remember { mutableStateOf(false) }
                Text(
                    text = post.title,
                    style = textStyle,
                    softWrap = true,
                    modifier = Modifier
                        .height(80.dp)
                        .width(170.dp)
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
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(40)),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = NeonGreen,
                        contentColor = Color.White
                    ),
                    onClick = {navController.navigate("post/${post.id}") }) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "View Post",
                        color = Jet,
                        fontSize = 16.sp,
                        fontFamily = K2D,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


