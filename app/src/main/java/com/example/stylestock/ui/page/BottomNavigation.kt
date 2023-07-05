package com.example.stylestock.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.stylestock.repository.UserStore
import com.example.stylestock.ui.theme.Jet
import com.example.stylestock.ui.theme.NeonBlue
import com.example.stylestock.ui.theme.WhiteSmoke
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


@Composable
fun BottomNavigation(navController: NavController) {
    val context = LocalContext.current

    val items = runBlocking {
        if (UserStore(context).getIsBrand.first()){
            return@runBlocking listOf(
                BottomNavItem.AddPost,
                BottomNavItem.Profil,
            )
        }else{
            return@runBlocking listOf(
                BottomNavItem.Trend,
                BottomNavItem.NewBrand,
                BottomNavItem.Follow,
                BottomNavItem.Notification,
                BottomNavItem.Profil,
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter,

    ) {
        androidx.compose.material.BottomNavigation(
            backgroundColor = Jet,
            modifier = Modifier
                .width(375.dp)
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .height(67.dp),
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen ->
                BottomNavigationItem(
                    modifier = Modifier
                        .offset(y = 19.dp)
                        .size(30.dp),
                    selected = currentDestination?.hierarchy?.any { it.route == screen.screen_route } == true,
                    icon = {
                        val iconRes =
                            if (currentDestination?.hierarchy?.any { it.route == screen.screen_route } == true) {
                                screen.icon_fill
                            } else {
                                screen.icon_empty
                            }
                        Icon(
                            tint = WhiteSmoke,
                            painter = painterResource(id = iconRes),
                            contentDescription = null
                        )
                    },
                    onClick = {
                        navController.navigate(screen.screen_route)
                    },
                )
            }
        }
    }
}