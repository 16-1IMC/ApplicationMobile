package com.example.stylestock.ui.page

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylestock.R
import com.example.stylestock.modele.ApiKey
import com.example.stylestock.modele.User
import com.example.stylestock.modele.UserALl
import com.example.stylestock.repository.ApiRepository
import com.example.stylestock.repository.UserRepository
import com.example.stylestock.repository.UserStore
import com.example.stylestock.ui.component.TitleComponent
import com.example.stylestock.ui.theme.Jet
import com.example.stylestock.ui.theme.Jura
import com.example.stylestock.ui.theme.K2D
import com.example.stylestock.ui.theme.NeonBlue
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(76.dp))
        Image(
            painter = painterResource(
                id = R.drawable.logo_titre
            ),
            contentDescription = "logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(start = 13.dp)
                .width(250.dp)
        )
        Spacer(modifier = Modifier.height(65.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = "Login",
                color = Jet,
                fontSize = 30.sp,
                fontFamily = Jura,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 70.dp)
                    .height(45.dp)
                    .drawBehind {
                        drawLine(
                            color = NeonBlue,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 12f,
                            cap = StrokeCap.Round
                        )
                    }
            )
            Spacer(modifier = Modifier.height(25.dp))
            var textLogin by remember { mutableStateOf(TextFieldValue("")) }
            var textPassword by remember { mutableStateOf(TextFieldValue("")) }
            var textErrorLogin by remember { mutableStateOf("") }
            var textErrorPassword by remember { mutableStateOf("") }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    textStyle = TextStyle(
                        color = Jet,
                        fontSize = 20.sp,
                        fontFamily = K2D,
                        fontWeight = FontWeight.Normal,
                    ),
                    value = textLogin,
                    onValueChange = {
                        textLogin = it
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    supportingText = {
                        Text(
                            text = textErrorLogin,
                            style = TextStyle(
                                color = Color.Red,
                                fontSize = 10.sp,
                                fontFamily = K2D,
                                fontWeight = FontWeight.Normal,
                            )
                        )
                    },
                )

                var passwordVisible by remember { mutableStateOf(false) }
                OutlinedTextField(
                    textStyle = TextStyle(
                        color = Jet,
                        fontSize = 20.sp,
                        fontFamily = K2D,
                        fontWeight = FontWeight.Normal,
                    ),
                    value = textPassword,
                    onValueChange = {
                        textPassword = it
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

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, description)
                        }
                    },
                    supportingText = {
                        Text(
                            text = textErrorPassword,
                            style = TextStyle(
                                color = Color.Red,
                                fontSize = 10.sp,
                                fontFamily = K2D,
                                fontWeight = FontWeight.Normal,
                            )
                        )
                    },
                )
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
                            if (Patterns.EMAIL_ADDRESS.matcher(textLogin.text).matches()) {
                                val resToken = ApiRepository().getToken(
                                    textLogin.text,
                                    textPassword.text
                                )
                                if (resToken != "") {
                                    val token = Gson().fromJson(
                                        resToken, ApiKey::class.java
                                    )
                                    val resUser =
                                        UserRepository(token.token).getUserByEmail(textLogin.text)
                                    if (resUser != "") {
                                        Log.d("styleStock", resUser)
                                        val user =
                                            Gson().fromJson(resUser, Array<UserALl>::class.java)
                                        if (user.isNotEmpty()) {
                                            UserStore(context).saveToken(
                                                token.token,
                                                user[0].id.toString(),
                                                false
                                            )
                                            navController.navigate("follow")
                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Something goes wrong",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    textErrorLogin = ""
                                    textErrorPassword = "Wrong Email or password"
                                }
                            } else {
                                textErrorLogin = "Enter a valid email address"
                                textErrorPassword = ""
                            }
                        }
                    }) {
                    Text(
                        text = "Login",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontFamily = K2D,
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row() {
                    Text(
                        text = "Don't have an account ? ",
                        color = Jet,
                        fontSize = 16.sp,
                        fontFamily = K2D,
                        fontWeight = FontWeight.Normal
                    )
                    ClickableText(
                        text = AnnotatedString("Register now"),
                        onClick = { navController.navigate("register") },
                        style = TextStyle(
                            color = NeonBlue,
                            fontSize = 16.sp,
                            fontFamily = K2D,
                            fontWeight = FontWeight.Normal,
                        )
                    )

                }
                Row() {
                    Text(
                        text = "Brand ? ",
                        color = Jet,
                        fontSize = 16.sp,
                        fontFamily = K2D,
                        fontWeight = FontWeight.Normal
                    )
                    ClickableText(
                        text = AnnotatedString("Login here"),
                        onClick = { navController.navigate("loginBrand") },
                        style = TextStyle(
                            color = NeonBlue,
                            fontSize = 16.sp,
                            fontFamily = K2D,
                            fontWeight = FontWeight.Normal,
                        )
                    )

                }
            }
        }
    }

}


