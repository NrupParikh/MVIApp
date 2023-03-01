package com.nrup.mviapp.ui.main.intent

sealed class MainIntent {
    object FetchUser : MainIntent()
}