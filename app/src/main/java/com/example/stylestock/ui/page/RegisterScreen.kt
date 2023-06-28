package com.example.stylestock.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylestock.R
import com.example.stylestock.ui.component.TitleComponent
import com.example.stylestock.ui.theme.Jet
import com.example.stylestock.ui.theme.Jura
import com.example.stylestock.ui.theme.K2D
import com.example.stylestock.ui.theme.NeonBlue


@Composable
fun RegisterScreen(navController: NavController) {

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
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = NeonBlue,
                        unfocusedBorderColor = Color.Black,
                        focusedLabelColor = NeonBlue,
                        unfocusedLabelColor = Color.Black,
                        cursorColor = NeonBlue,
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))
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
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = NeonBlue,
                        unfocusedBorderColor = Color.Black,
                        focusedLabelColor = NeonBlue,
                        unfocusedLabelColor = Color.Black,
                        cursorColor = NeonBlue,
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))
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
                    label = { Text(text = "Confirm Password") },
                    placeholder = { Text(text = "Confirm your Password") },
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = NeonBlue,
                        unfocusedBorderColor = Color.Black,
                        focusedLabelColor = NeonBlue,
                        unfocusedLabelColor = Color.Black,
                        cursorColor = NeonBlue,
                    ),
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .width(250.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = NeonBlue,
                        contentColor = Color.White
                    ),
                    onClick = { navController.navigate("login") }) {
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
                        onClick = {navController.navigate("login")},
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
                        onClick = {},
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


