package com.nrup.mviapp.data.repository

import com.nrup.mviapp.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}