package com.example.stylestock.modele

data class User(
    val id: Int,
    val email: String,
    val creationDate: String,
    val likes: List<Post>,
    val follows: List<Brand>,
)

data class Users(
    val users: List<User>
)
