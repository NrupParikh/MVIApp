package com.nrup.mviapp.ui.main.viewstate

import com.nrup.mviapp.data.model.User

sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String?) : MainState()
}