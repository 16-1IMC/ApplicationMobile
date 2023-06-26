package com.example.stylestock.modele

data class User(
    val id: Int,
    val email: String,
    val password: String,
    val isAdmin: Boolean,
    val likes: List<Post>,
    val creationDate: String,
)
