package com.example.rickandmorty.data

import retrofit2.http.GET

interface CharacterApi {
    @GET("character")
    suspend fun getAll(): RequestResult
}