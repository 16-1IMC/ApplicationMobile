package com.example.stylestock.ui.page

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylestock.R
import com.example.stylestock.repository.ApiRepository
import com.example.stylestock.repository.UserRepository
import com.example.stylestock.ui.component.TitleComponent
import com.example.stylestock.ui.theme.Jet
import com.example.stylestock.ui.theme.Jura
import com.example.stylestock.ui.theme.K2D
import com.example.stylestock.ui.theme.NeonBlue
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
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
                text = "Register",
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
                            strokeWidth = 6f,
                            cap = StrokeCap.Round
                        )
                    }
            )
            Spacer(modifier = Modifier.height(25.dp))
            var textLogin by remember { mutableStateOf(TextFieldValue("")) }
            var textPassword by remember { mutableStateOf(TextFieldValue("")) }
            var textConfirmPassword by remember { mutableStateOf(TextFieldValue("")) }
            var textErrorLogin by remember { mutableStateOf("") }
            var textErrorPassword by remember { mutableStateOf("") }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                OutlinedTextField(
                    textStyle = androidx.compose.ui.text.TextStyle(
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
                    textStyle = androidx.compose.ui.text.TextStyle(
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

                        androidx.compose.material3.IconButton(onClick = {
                            passwordVisible = !passwordVisible
                        }) {
                            androidx.compose.material3.Icon(imageVector = image, description)
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
                    value = textConfirmPassword,
                    onValueChange = {
                        textConfirmPassword = it
                    },
                    singleLine = true,
                    label = { Text(text = "Confirm Password") },
                    placeholder = { Text(text = "Confirm your Password") },
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

                        androidx.compose.material3.IconButton(onClick = {
                            passwordConfirmVisible = !passwordConfirmVisible
                        }) {
                            androidx.compose.material3.Icon(imageVector = image, description)
                        }
                    },
                )
                Spacer(modifier = Modifier.height(15.dp))
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
                            if (android.util.Patterns.EMAIL_ADDRESS.matcher(textLogin.text)
                                    .matches()
                            ) {

                                if (textPassword.text == textConfirmPassword.text) {
                                    val res =
                                        UserRepository().createUser(
                                            textLogin.text,
                                            textPassword.text
                                        )

                                    if (res != "") {
                                        Log.d("styleStock", "Create user")
                                        navController.navigate("login")
                                        Toast.makeText(
                                            context,
                                            "Your account has been created",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        navController.navigate("login")
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Something went wrong",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    textErrorPassword = "Password not match"
                                    textErrorLogin = ""
                                }
                            } else {
                                textErrorLogin = "Invalid email address"
                                textErrorPassword = ""
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
                Spacer(modifier = Modifier.height(40.dp))
                Row() {
                    Text(
                        text = "Allready register ? ",
                        color = Jet,
                        fontSize = 16.sp,
                        fontFamily = K2D,
                        fontWeight = FontWeight.Normal
                    )
                    ClickableText(
                        text = AnnotatedString("Sign in"),
                        onClick = { navController.navigate("login") },
                        style = androidx.compose.ui.text.TextStyle(
                            color = NeonBlue,
                            fontSize = 16.sp,
                            fontFamily = K2D,
                            fontWeight = FontWeight.Normal,
                        )
                    )

                }
                Spacer(modifier = Modifier.height(40.dp))
                Row() {
                    Text(
                        text = "Are you a brand ? ",
                        color = Jet,
                        fontSize = 16.sp,
                        fontFamily = K2D,
                        fontWeight = FontWeight.Normal
                    )
                    ClickableText(
                        text = AnnotatedString("Contact us"),
                        onClick = {


                        },
                        style = androidx.compose.ui.text.TextStyle(
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


