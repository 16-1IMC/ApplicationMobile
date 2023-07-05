package com.example.stylestock.ui.page

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.stylestock.repository.ImageRepository
import com.example.stylestock.repository.UserStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.apache.commons.io.FileUtils
import java.io.ByteArrayOutputStream
import java.io.File

import java.io.IOException

import java.io.InputStream


@Composable
fun CreatePostScreen(navController: NavController) {
    val context = LocalContext.current
    var selectedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> selectedImageUris = uris }
    )

    LazyColumn {
        item {
            Button(
                onClick = {
                    multiplePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            ) {
                Text(text = "Pick multiple photo")
            }
        }
        items(selectedImageUris) {uri->

            runBlocking {
                val file = createTmpFileFromUri(context, uri, "tmp")
                ImageRepository(UserStore(context).getAccessToken.first()).createImage(file!!)
            }
        }

    }
}

fun createTmpFileFromUri(context: Context, uri: Uri, fileName: String): File? {
    return try {
        val stream = context.contentResolver.openInputStream(uri)
        val file = File.createTempFile(fileName, "", context.cacheDir)
        FileUtils.copyInputStreamToFile(stream,file)
        file
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


