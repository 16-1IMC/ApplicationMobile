package com.example.stylestock.modele

data class Brand(
    val id: Int,
    val name: String,
    val description: String,
    val logo: Image = Image(0,"",""),
    val banner: Image = Image(0,"",""),
    val email: String ="",
    val password: String = "",
    val socialNetworks: List<SocialNetwork> = emptyList(),
    val categories: List<Category> = emptyList(),
    val posts: List<Post> = emptyList(),
    val creationDate: String = "",
)
