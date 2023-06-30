package com.example.stylestock.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stylestock.modele.Adidas
import com.example.stylestock.ui.component.BrandSearchComponent
import com.example.stylestock.ui.component.Header
import com.example.stylestock.ui.component.TitleClickableComponent
import com.example.stylestock.ui.component.TitleComponent


@Composable
fun SearchBrandScreen(navController: NavController) {
    Column {
        Header(navController)
        Row(
            modifier = Modifier
                .padding(start = 33.dp, end = 66.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TitleClickableComponent(text ="Brand",true, { navController.navigate("searchBrand") })
            TitleClickableComponent(text ="Post",false, { navController.navigate("searchPost") })
        }
        Column(
            modifier = Modifier
                .padding(start = 33.dp)
                .verticalScroll(rememberScrollState())
        ) {
            BrandSearchComponent(Adidas)
        }
        Spacer(
            modifier = Modifier
                .height(70.dp)
        )
    }
}