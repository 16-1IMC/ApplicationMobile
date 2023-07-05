package com.example.stylestock.modele

data class Follow(
    val id: Int,
    val user: User,
    val brand: Brand,
    val created_at: String = "",
)

data class FollowAll(
    val id: Int,
    val user: String,
    val brand: String,
    val created_at: String = "",
)