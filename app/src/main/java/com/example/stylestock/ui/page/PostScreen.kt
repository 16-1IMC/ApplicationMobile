package com.example.stylestock.ui.page

import android.media.tv.TvContract.Channels.Logo
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.stylestock.R
import com.example.stylestock.modele.AdidasPost
import com.example.stylestock.modele.Post
import com.example.stylestock.repository.LikeRepository
import com.example.stylestock.repository.PostRepository
import com.example.stylestock.repository.UserRepository
import com.example.stylestock.repository.UserStore
import com.example.stylestock.ui.component.BackArrowComponent
import com.example.stylestock.ui.component.DotIndicatorsComponent
import com.example.stylestock.ui.component.LogoBrand
import com.example.stylestock.ui.component.TitleComponent
import com.example.stylestock.ui.theme.Jet
import com.example.stylestock.ui.theme.Jura
import com.example.stylestock.ui.theme.K2D
import com.example.stylestock.ui.theme.NeonGreen
import com.example.stylestock.ui.theme.WhiteSmoke
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostScreen(navController: NavController, postId: String?) {
    var context = LocalContext.current
    val apiKey by remember { derivedStateOf { runBlocking { UserStore(context).getAccessToken.first() } } }
    val isBrand by remember { derivedStateOf { runBlocking { UserStore(context).getIsBrand.first() } } }
    var post by remember { mutableStateOf<Post?>(null) }
    var isLiked by remember { mutableStateOf(true) }
    var likeCount by remember { mutableStateOf(0) }

    var readyToDraw by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        val userStore = UserStore(context)
        val accessToken = userStore.getAccessToken.first()
        val userId = userStore.getUserId.first()

        isLiked = UserRepository(accessToken).getUserIsLiked(userId, postId!!)
        post = PostRepository(accessToken).getPostById(postId)
        likeCount = PostRepository(UserStore(context).getAccessToken.first()).getNbLike(
            postId!!
        )
    }

    post?.let { post ->
        BoxWithConstraints {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(modifier = Modifier.aspectRatio(1f / 1)) {
                    val state = rememberPagerState()
                    HorizontalPager(
                        modifier = Modifier.fillMaxSize(),
                        pageCount = post.images.size,
                        state = state
                    ) { page ->
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(WhiteSmoke),
                            contentScale = ContentScale.FillWidth,
                            model = post.images[page].toString(),
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
                        .padding(vertical = 5.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(6.dp))
                    LogoBrand(navController = navController, brand = post.author, size = 66.dp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                    ) {
                        val textStyleBody = TextStyle(
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Jura
                        )
                        var textStyle by remember { mutableStateOf(textStyleBody) }
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
                                    textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9)
                                } else {
                                    readyToDraw = true
                                }
                            }
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            val likeRepository = LikeRepository(apiKey)
                            val userStore = UserStore(context)
                            val currentUserId =
                                runBlocking { userStore.getUserId.first() }

                            suspend fun updateLikeState(): String {
                                if (isLiked) {
                                    likeCount -= 1
                                    return likeRepository.removeLike(
                                        currentUserId,
                                        postId!!
                                    )

                                } else {
                                    likeCount += 1
                                    return likeRepository.addLike(
                                        currentUserId,
                                        postId!!
                                    )

                                }
                            }
                            Text(
                                text = "$likeCount Likes",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = K2D,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            if (!isBrand) {

                                IconButton(
                                    modifier = Modifier.size(20.dp),
                                    onClick = {
                                        runBlocking {
                                            updateLikeState()
                                            isLiked = !isLiked

                                        }
                                    }
                                ) {

                                    Image(
                                        painter = painterResource(id = if (isLiked) R.drawable.like_fille_icon else R.drawable.like_empty_icon),
                                        contentDescription = "Like Icon",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
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
                        post.author.categories.forEach {
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
                            containerColor = NeonGreen,
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
                Spacer(modifier = Modifier.height(200.dp))
            }
            BackArrowComponent(navController)
        }
    }
}

