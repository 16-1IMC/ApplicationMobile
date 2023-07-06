package com.example.stylestock.ui.page

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylestock.modele.ApiKey
import com.example.stylestock.modele.Brand
import com.example.stylestock.modele.User
import com.example.stylestock.modele.UserALl
import com.example.stylestock.repository.ApiRepository
import com.example.stylestock.repository.BrandRepository
import com.example.stylestock.repository.FollowRepository
import com.example.stylestock.repository.UserRepository
import com.example.stylestock.repository.UserStore
import com.example.stylestock.ui.component.BrandComponent
import com.example.stylestock.ui.component.BrandSearchComponent
import com.example.stylestock.ui.component.Header
import com.example.stylestock.ui.component.NavBar
import com.example.stylestock.ui.component.TitleComponent
import com.example.stylestock.ui.theme.K2D
import com.example.stylestock.ui.theme.NeonBlue
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun ProfilScreen(navController: NavController) {
    val context = LocalContext.current
    val apiKey = runBlocking { UserStore(context).getAccessToken.first() }
    val isBrand = runBlocking { UserStore(context).getIsBrand.first() }
    var user by remember { mutableStateOf<User?>(null) }
    var follow by remember { mutableStateOf<List<Brand>>(emptyList()) }
    LaunchedEffect(Unit) {
        if (isBrand) {
            val brand = BrandRepository(apiKey).getBrandById(UserStore(context).getUserId.first())
            user = User(
                id = brand!!.id,
                email = brand!!.email,
                creationDate = brand!!.created_at
            )
        } else {
            val resUser = UserRepository(apiKey).getUserById(UserStore(context).getUserId.first())
            user = Gson().fromJson(resUser, User::class.java)
        }

        if (user != null) {
            if (!isBrand) {
                var resFollow = FollowRepository(apiKey).getFollowUser(user!!.id.toString())
                if (resFollow != null) {
                    follow = BrandRepository(apiKey).getBrandByMultipleId(resFollow)
                }
            } else {
                follow = BrandRepository(apiKey).getBrandByMultipleId(listOf(user!!.id.toString()))
            }
        }


    }

    user?.let { user ->
        Column(
        ) {
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
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .width(250.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NeonBlue,
                        contentColor = Color.White
                    ),
                    onClick = {
                        runBlocking {
                            UserStore(context).clearToken()
                            navController.navigate("Login")
                        }
                    }
                ) {
                    Text(
                        text = "Logout",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = K2D,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .width(250.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFDE2828),
                        contentColor = Color.White
                    ),
                    onClick = {
                        runBlocking {
                            UserRepository(apiKey).deleteUserById(user.id.toString())
                            UserStore(context).clearToken()
                            navController.navigate("login")
                        }
                    },
                    content = {
                        Text(
                            text = "Delete account",
                            style = TextStyle(
                                fontFamily = K2D,
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        )
                    }
                )
            }
            TitleComponent(text = "Follow", withBar = true)

            LazyColumn(
                modifier = Modifier
                    .padding(start = 33.dp),

                ) {
                items(follow) { brand ->
                    BrandSearchComponent(navController, brand)
                }
            }

            Spacer(
                modifier = Modifier
                    .height(70.dp)
            )
        }
    }
}