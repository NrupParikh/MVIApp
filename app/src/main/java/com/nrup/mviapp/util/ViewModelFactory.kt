package com.nrup.mviapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nrup.mviapp.data.api.ApiHelper
import com.nrup.mviapp.data.repository.MainRepository
import com.nrup.mviapp.ui.main.viewmodel.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}