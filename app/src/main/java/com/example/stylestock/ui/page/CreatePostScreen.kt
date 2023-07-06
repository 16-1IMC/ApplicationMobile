package com.example.stylestock.ui.page

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.stylestock.R
import com.example.stylestock.repository.ImageRepository
import com.example.stylestock.repository.PostRepository
import com.example.stylestock.repository.UserStore
import com.example.stylestock.ui.component.TitleComponent
import com.example.stylestock.ui.theme.NeonGreen
import com.example.stylestock.ui.theme.WhiteSmoke
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.commons.io.FileUtils
import java.io.ByteArrayOutputStream
import java.io.File

import java.io.IOException

import java.io.InputStream


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var selectedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> selectedImageUris = uris }
    )
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var state = rememberPagerState()

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(
                id = R.drawable.logo_titre
            ),
            contentDescription = "logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(start = 13.dp)
                .width(150.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TitleComponent(text = "Post Name", withBar = true)
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 33.dp, end = 33.dp),
            value = name, onValueChange = { name = it })
        Spacer(modifier = Modifier.height(20.dp))
        TitleComponent(text = "Description", withBar = true)
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 33.dp, end = 33.dp)
                .height(150.dp),
            value = description, onValueChange = { description = it })
        Spacer(modifier = Modifier.height(20.dp))
        TitleComponent(text = "Add Photo", withBar = true)
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            pageCount = selectedImageUris.size,
            state = state
        ) { page ->
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(1f / 1f)
                    .background(WhiteSmoke),
                contentScale = ContentScale.Inside,
                model = selectedImageUris[page],
                contentDescription = "image"
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .width(250.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonGreen,
                    contentColor = Color.White
                ),
                onClick = {
                    multiplePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            ) {
                Text(text = "Pick multiple photo")
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .width(250.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = NeonGreen,
                contentColor = Color.White
            ),
            onClick = {
                scope.launch {
                    val userStore = UserStore(context)
                    val apiKey = runBlocking { userStore.getAccessToken.first() }
                    val imageRepository = runBlocking { ImageRepository(apiKey) }
                    val userId = runBlocking { userStore.getUserId.first() }
                    val imageIds = mutableListOf<String>()
                    selectedImageUris.forEach { uri ->
                        val res = createTmpFileFromUri(context, uri, "${userId}_post")
                        if (res != null) {
                            val image = imageRepository.createImage(res)
                            imageIds.add(image!!.id.toString())
                        }
                    }

                    val post =
                        PostRepository(apiKey).createPost( name, description,userId, imageIds)
                    if (post != null) {
                        navController.navigate("post/${post!!.id}")
                    }
                }
            }
        ) {
            Text(text = "Create Post")
        }
        Spacer(modifier = Modifier.height(200.dp))

    }

}




