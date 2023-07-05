package com.example.stylestock.ui.page

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylestock.R
import com.example.stylestock.ui.component.Header
import com.example.stylestock.ui.component.TitleClickableComponent
import com.example.stylestock.ui.component.TitleComponent
import com.example.stylestock.ui.theme.K2D
import com.example.stylestock.ui.theme.NeonBlue


@Composable
fun SearchPostScreen(navController: NavController){
    var context = LocalContext.current
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
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
            var textSearch by remember { mutableStateOf(TextFieldValue("")) }
            TextField(
                modifier = Modifier
                    .border(
                        width = 3.dp,
                        color = NeonBlue,
                        shape = RoundedCornerShape(50)
                    ),
                singleLine = false,
                shape = RoundedCornerShape(50),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontFamily = K2D
                ),
                value = textSearch,
                placeholder = {
                    Text(
                        text = "Search",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 13.sp,
                            fontFamily = K2D
                        )
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        Log.d("styleStock", "search")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_search),

                            contentDescription = "searchButton",
                            modifier = Modifier
                                .padding(top = 10.dp, end = 20.dp)
                                .size(30.dp)
                        )
                    }
                },
                onValueChange = { textSearch = it },
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 33.dp, end = 66.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TitleClickableComponent(text ="Brand",false, { navController.navigate("searchBrand") })
            TitleClickableComponent(text ="Post",true, { navController.navigate("searchPost") })
        }
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