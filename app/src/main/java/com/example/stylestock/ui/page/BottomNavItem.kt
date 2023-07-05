package com.example.stylestock.ui.page

import com.example.stylestock.R

sealed class BottomNavItem(var title:String, var icon_empty:Int,var icon_fill:Int,var screen_route:String){
    object Follow:BottomNavItem("Follow", R.drawable.follow_logo_empty,R.drawable.follow_logo_fill,"follow")
    object Trend:BottomNavItem("Trend", R.drawable.trend_logo_empty,R.drawable.trend_logo_fill,"trend")
    object NewBrand:BottomNavItem("New", R.drawable.new_logo_empty,R.drawable.new_logo_fill,"new")
    object Profil:BottomNavItem("Profil", R.drawable.profil_logo_empty,R.drawable.profil_logo_fill,"profil")
    object Notification:BottomNavItem("Notification", R.drawable.notification_logo_empty,R.drawable.notification_logo_fill,"notification")

    object AddPost:BottomNavItem("AddPost", R.drawable.add_icon,R.drawable.add_icon,"addPost")
}
