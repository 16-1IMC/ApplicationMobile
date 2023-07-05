package com.example.stylestock.modele

data class Like(
    val id: Int,
    val user: User,
    val post: Post,
    val created_at: String = "",
)

data class LikeAll(
    val id: Int,
    val user: String,
    val post: String,
    val created_at: String = "",
)