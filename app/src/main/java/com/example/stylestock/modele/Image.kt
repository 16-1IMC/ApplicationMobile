package com.example.stylestock.modele

import java.util.Date

data class Image(
    val id: Int,
    val path: String,
    val creationDate: String,){

    override fun toString(): String {
        return "http://thegoodnetwork.fr/images/$path"
    }
}
