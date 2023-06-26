package com.example.stylestock.modele

data class Tag(
    val id: Int,
    val name: String,
    val creationDate: String,
    val posts: List<Post>,
)
