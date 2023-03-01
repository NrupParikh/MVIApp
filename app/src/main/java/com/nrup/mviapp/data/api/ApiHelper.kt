package com.nrup.mviapp.data.api

import com.nrup.mviapp.data.model.User

interface ApiHelper {
    suspend fun getUsers(): List<User>
}