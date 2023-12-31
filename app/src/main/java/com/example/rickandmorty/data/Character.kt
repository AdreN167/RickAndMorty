package com.example.rickandmorty.data

data class Character(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val origin: NameAndURLObject?,
    val location: NameAndURLObject?,
    val image: String?,
    val episode: List<String>?,
    val url: String?,
    val created: String?
)
