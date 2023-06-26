package com.example.stylestock.modele

data class Brand(
    val id: Int,
    val name: String,
    val logo: Image,
    val email: String,
    val password: String,
    val socialNetworks: List<SocialNetwork>,
    val categories: List<Category>,
    val posts: List<Post>,
    val creationDate: String,
)
