package com.example.stylestock.modele

data class User(
    val id: Int,
    val email: String,
    val creationDate: String,
    val likes: List<Post> = emptyList(),
    val follows: List<Brand> = emptyList(),
)

data class UserALl(
    val id: Int,
    val email: String,
)
