package com.example.e_commerceapp.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.data.local.db.DbRepositorySource

abstract class BaseViewModel(private val databaseRepository: DbRepositorySource, private val savedStateHandle : SavedStateHandle) : ViewModel()  {

    val isLoading = ObservableBoolean()

    fun getDataManager() : DbRepositorySource = databaseRepository

    fun getSaveStateHandle() : SavedStateHandle = savedStateHandle

    fun setIsLoading(b:Boolean){
        isLoading.set(b)
    }
}