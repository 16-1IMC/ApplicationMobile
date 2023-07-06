package com.example.stylestock.ui.page

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.stylestock.repository.BrandRepository
import com.example.stylestock.repository.ImageRepository
import com.example.stylestock.repository.UserRepository
import com.example.stylestock.repository.UserStore
import com.example.stylestock.ui.theme.Jet
import com.example.stylestock.ui.theme.K2D
import com.example.stylestock.ui.theme.NeonBlue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.commons.io.FileUtils
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterBrandScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordCheck by remember { mutableStateOf("") }
    var logoSelectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var bannerSelectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val logoSinglePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> logoSelectedImageUri = uri }
    )
    val bannerSinglePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> bannerSelectedImageUri = uri }
    )
    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            textStyle = TextStyle(
                color = Jet,
                fontSize = 20.sp,
                fontFamily = K2D,
                fontWeight = FontWeight.Normal,
            ),
            value = name,
            onValueChange = {
                name = it
            },
            singleLine = true,
            label = { Text(text = "Name") },
            placeholder = { Text(text = "Enter your brand name") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = NeonBlue,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = NeonBlue,
                unfocusedLabelColor = Color.Black,
                cursorColor = NeonBlue,
            ),
        )
        OutlinedTextField(
            textStyle = TextStyle(
                color = Jet,
                fontSize = 20.sp,
                fontFamily = K2D,
                fontWeight = FontWeight.Normal,
            ),
            value = description,
            onValueChange = {
                description = it
            },
            singleLine = true,
            label = { Text(text = "Description") },
            placeholder = { Text(text = "Enter your brand description") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = NeonBlue,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = NeonBlue,
                unfocusedLabelColor = Color.Black,
                cursorColor = NeonBlue,
            ),
        )
        OutlinedTextField(
            textStyle = TextStyle(
                color = Jet,
                fontSize = 20.sp,
                fontFamily = K2D,
                fontWeight = FontWeight.Normal,
            ),
            value = email,
            onValueChange = {
                email = it
            },
            singleLine = true,
            label = { Text(text = "Email address") },
            placeholder = { Text(text = "Enter your e-mail") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = NeonBlue,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = NeonBlue,
                unfocusedLabelColor = Color.Black,
                cursorColor = NeonBlue,
            ),
        )
        var passwordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            textStyle = TextStyle(
                color = Jet,
                fontSize = 20.sp,
                fontFamily = K2D,
                fontWeight = FontWeight.Normal,
            ),
            value = password,
            onValueChange = {
                password = it
            },
            singleLine = true,
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter your password") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = NeonBlue,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = NeonBlue,
                unfocusedLabelColor = Color.Black,
                cursorColor = NeonBlue,
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    Icon(imageVector = image, description)
                }
            },
        )
        var passwordConfirmVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            textStyle = androidx.compose.ui.text.TextStyle(
                color = Jet,
                fontSize = 20.sp,
                fontFamily = K2D,
                fontWeight = FontWeight.Normal,
            ),
            value = passwordCheck,
            onValueChange = {
                passwordCheck = it
            },
            singleLine = true,
            label = { Text(text = "Confirm Password") },
            placeholder = { Text(text = "Confirm your Password") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = NeonBlue,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = NeonBlue,
                unfocusedLabelColor = Color.Black,
                cursorColor = NeonBlue,
            ),
            visualTransformation = if (passwordConfirmVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordConfirmVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordConfirmVisible) "Hide password" else "Show password"

                IconButton(onClick = {
                    passwordConfirmVisible = !passwordConfirmVisible
                }) {
                    Icon(imageVector = image, description)
                }
            },
        )
        Row(
            Modifier.fillMaxWidth()
        ) {
            Column(
                Modifier.weight(1f)
            ) {
                AsyncImage(
                    model = logoSelectedImageUri,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Button(
                    onClick = {
                        logoSinglePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                ) {
                    Text(text = "Choose Logo")
                }
            }
            Column(
                Modifier.weight(1f)
            ) {
                AsyncImage(
                    model = bannerSelectedImageUri,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Button(
                    onClick = {
                        bannerSinglePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                ) {
                    Text(text = "Choose Banner")
                }
            }
        }
        Button(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .width(250.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = NeonBlue,
                contentColor = Color.White
            ),
            onClick = {

                scope.launch {
                    if (password != passwordCheck) {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    if (logoSelectedImageUri != null && bannerSelectedImageUri != null) {
                        val resLogo = createTmpFileFromUri(context, logoSelectedImageUri!!, "logo")
                        val resBanner = createTmpFileFromUri(context, bannerSelectedImageUri!!, "banner")
                        if (resLogo != null) {
                            val logo = ImageRepository().createImage(resLogo!!)
                            val banner = ImageRepository().createImage(resBanner!!)
                            Log.d("stylStock", logo!!.id.toString())
                            if (logo != null && banner != null) {
                                val result = BrandRepository().createBrand(
                                    name = name,
                                    email = email,
                                    description = description,
                                    plainPassword = password,
                                    profilePicture = logo.id.toString(),
                                    banner = banner.id.toString(),
                                )
                                if(result != null) {
                                    Toast.makeText(context, "Brand created", Toast.LENGTH_SHORT)
                                        .show()
                                    navController.navigate("loginBrand")
                                }
                            }
                        }
                    }
                }
            }) {
            Text(
                text = "Register",
                color = Color.White,
                fontSize = 28.sp,
                fontFamily = K2D,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

fun createTmpFileFromUri(context: Context, uri: Uri, fileName: String): File? {
    return try {
        val stream = context.contentResolver.openInputStream(uri)
        val file = File.createTempFile(fileName, "", context.cacheDir)
        FileUtils.copyInputStreamToFile(stream, file)
        file
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}