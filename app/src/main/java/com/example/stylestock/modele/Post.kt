package com.example.stylestock.modele

data class Post(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val brand: Brand = Brand(),
    val images: List<Image> = emptyList(),
    val tags: List<Tag> = emptyList(),
    val creationDate: String = "",
)
