package com.example.stylestock.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stylestock.modele.Adidas
import com.example.stylestock.modele.AdidasPost
import com.example.stylestock.modele.Post
import com.example.stylestock.repository.FollowRepository
import com.example.stylestock.repository.PostRepository
import com.example.stylestock.repository.UserStore
import com.example.stylestock.ui.component.BrandComponent
import com.example.stylestock.ui.component.Header
import com.example.stylestock.ui.component.PostComponent
import com.example.stylestock.ui.component.TitleComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


@Composable
fun MyFollowScreen(navController: NavController) {
    val context = LocalContext.current
    var posts by remember { mutableStateOf<List<Post>>(emptyList()) }

    LaunchedEffect(Unit) {
        val apiKey = UserStore(context).getAccessToken.first()
        val userId = UserStore(context).getUserId.first()
        val followBrand = FollowRepository(apiKey).getFollowUser(userId)
        if (followBrand != null) {
            posts = PostRepository(apiKey).getPostByBrandIdMultiple(followBrand)
        }
        Log.d("styleStock", "followBrand: $followBrand")
    }

    Column {
        Header(navController)
        TitleComponent("My Follow", true)
        LazyColumn(
            modifier = Modifier
                .padding(start = 33.dp, bottom = 90.dp),
            ) {
            items(posts) { post ->
                PostComponent(navController = navController, post = post)
            }
        }


    }
}