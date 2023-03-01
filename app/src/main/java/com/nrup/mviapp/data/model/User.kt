package com.nrup.mviapp.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// https://5e510330f2c0d300147c034c.mockapi.io/users

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "avatar")
    val avatar: String,
    @Json(name = "email")
    val email: String,
)