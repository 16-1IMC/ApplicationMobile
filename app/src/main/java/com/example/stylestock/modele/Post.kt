package com.example.stylestock.modele

data class Post(
    val id: Int,
    val title: String,
    val content: String,
    val brand: Brand,
    val image: List<Image>,
    val tags: List<Tag> = emptyList(),
    val creationDate: String = "",

)
