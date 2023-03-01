package com.nrup.mviapp.data.api

import com.nrup.mviapp.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}