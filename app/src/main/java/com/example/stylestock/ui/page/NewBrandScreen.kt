package com.example.stylestock.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stylestock.modele.Adidas
import com.example.stylestock.modele.Brand
import com.example.stylestock.repository.BrandRepository
import com.example.stylestock.repository.UserStore
import com.example.stylestock.ui.component.BrandComponent
import com.example.stylestock.ui.component.Header
import com.example.stylestock.ui.component.TitleComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun NewBrandScreen(navController: NavController) {
    val context = LocalContext.current
    var brands by remember { mutableStateOf<List<Brand>>(emptyList()) }

    LaunchedEffect(Unit) {
        val accessToken = UserStore(context).getAccessToken.first()
        val res = BrandRepository(accessToken).getBrandAll()
        if (res != null) {
            brands = res
        }
    }

    Column {
        Header(navController)
        TitleComponent(text = "New Brand", withBar = true)
        LazyColumn(
            modifier = Modifier
                .padding(start = 33.dp, bottom = 90.dp),

        ) {
            items(brands) { brand ->
                BrandComponent(navController, brand)
            }
        }
    }
}
