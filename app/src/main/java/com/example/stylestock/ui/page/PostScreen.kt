package com.example.stylestock.ui.page

import android.media.tv.TvContract.Channels.Logo
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.stylestock.R
import com.example.stylestock.modele.AdidasPost
import com.example.stylestock.ui.component.BackArrowComponent
import com.example.stylestock.ui.component.DotIndicatorsComponent
import com.example.stylestock.ui.component.LogoBrand
import com.example.stylestock.ui.component.TitleComponent
import com.example.stylestock.ui.theme.Jet
import com.example.stylestock.ui.theme.Jura
import com.example.stylestock.ui.theme.K2D
import com.example.stylestock.ui.theme.NeonGreen
import com.example.stylestock.ui.theme.WhiteSmoke


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostScreen(navController: NavController, postId: String?) {
    val post = AdidasPost
    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Box() {
                val state = rememberPagerState()
                HorizontalPager(
                    pageCount = post.images.size,
                    state = state
                ) { page ->
                    AsyncImage(
                        modifier = Modifier
                            .background(WhiteSmoke)
                            .fillMaxWidth()
                            .height(390.dp),
                        contentScale = ContentScale.FillWidth,
                        model = post.images[page].path,
                        contentDescription = "image"
                    )
                }
                DotIndicatorsComponent(
                    pageCount = post.images.size,
                    pagerState = state,
                )
            }
            Row(
                modifier = Modifier
                    .background(Jet)
                    .padding(top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(6.dp))
                LogoBrand(navController = navController, brand = post.brand, size = 66.dp)
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    val textStyleBody = androidx.compose.ui.text.TextStyle(
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Jura
                    )
                    var textStyle by remember { mutableStateOf(textStyleBody) }
                    var readyToDraw by remember { mutableStateOf(false) }
                    Text(
                        text = post.title,
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "1200 Likes",
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 16.sp,
                                fontFamily = K2D,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            ),
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        IconButton(
                            modifier = Modifier
                                .size(20.dp),
                            onClick = {

                            }) {
                            Image(
                                painter = painterResource(id = R.drawable.like_empty_icon),
                                contentDescription = "back",
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                    }

                }

            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

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
                    post.brand.categories.forEach {
                        Text(
                            text = it.name,
                            fontSize = 16.sp,
                            fontFamily = K2D,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            TitleComponent(text = "Description", withBar = true)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = post.content,
                color = Color.Black,
                fontFamily = K2D,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 33.dp, end = 33.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(RoundedCornerShape(25.dp)),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = NeonGreen,
                        contentColor = Color.Black,
                        ),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.link_icon),
                            contentDescription = "linkIcon",
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "See it",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 25.sp,
                                fontFamily = K2D,
                                fontWeight = FontWeight.Normal
                            )
                        )

                    }
                }
            }
        }
        BackArrowComponent(navController)
    }
}