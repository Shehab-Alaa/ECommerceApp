package com.example.e_commerceapp.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

abstract class BaseViewModel(private val dataManager: DataManagerSource, private val savedStateHandle : SavedStateHandle) : ViewModel()  {

    val isLoading = ObservableBoolean()

    fun getDataManager() : DataManagerSource = dataManager

    fun getSaveStateHandle() : SavedStateHandle = savedStateHandle

    fun setIsLoading(b:Boolean){
        isLoading.set(b)
    }
}