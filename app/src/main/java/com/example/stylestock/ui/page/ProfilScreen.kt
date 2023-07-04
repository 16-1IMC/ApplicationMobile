package com.example.stylestock.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylestock.modele.User
import com.example.stylestock.repository.ApiRepository
import com.example.stylestock.repository.UserRepository
import com.example.stylestock.repository.UserStore
import com.example.stylestock.ui.component.Header
import com.example.stylestock.ui.component.NavBar
import com.example.stylestock.ui.component.TitleComponent
import com.example.stylestock.ui.theme.K2D
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun ProfilScreen(navController: NavController) {
    val context = LocalContext.current
    val apiKey = runBlocking { UserStore(context).getAccessToken.first() }
    val resUser =
        runBlocking { UserRepository(apiKey).getUserById(UserStore(context).getUserId.first()) }
    val user = Gson().fromJson(resUser, User::class.java)
    Column {
        Header(navController)
        TitleComponent(text = "Profil", true)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 33.dp, end = 33.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = user.email,
                style = TextStyle(
                    fontFamily = K2D,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )
            Button(
                content = {
                    Text(
                        text = "Logout",
                        style = TextStyle(
                            fontFamily = K2D,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
                },
                onClick = {
                    runBlocking {
                        UserStore(context).clearToken()
                        navController.navigate("login")
                    }
                },
            )
        }
        Button(
            modifier = Modifier
                .padding(start = 33.dp, end = 33.dp)
                .fillMaxWidth(),
            onClick = { runBlocking {
                UserRepository(apiKey).deleteUserById(user.id.toString())
                UserStore(context).clearToken()
                navController.navigate("login")
            } },
            content = {
                Text(
                    text = "Delete account",
                    style = TextStyle(
                        fontFamily = K2D,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
            }
        )
        Column(
            modifier = Modifier
                .padding(start = 33.dp)
                .verticalScroll(rememberScrollState())
        ) {
        }
        Spacer(
            modifier = Modifier
                .height(70.dp)
        )
    }
}