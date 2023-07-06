package com.example.stylestock.modele

data class Post(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val author: Brand = Brand(),
    val images: List<Image> = emptyList(),
    val creationDate: String = "",
)

data class PostCreate(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val author: String = "",
    val images: List<String> = emptyList(),
)

