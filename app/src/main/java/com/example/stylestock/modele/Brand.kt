package com.example.stylestock.modele

data class Brand(
    val id: Int,
    val name: String,
    val logo: Image,
    val banner: Image,
    val email: String ="",
    val password: String = "",
    val socialNetworks: List<SocialNetwork> = emptyList(),
    val categories: List<Category> = emptyList(),
    val posts: List<Post> = emptyList(),
    val creationDate: String = "",
)
