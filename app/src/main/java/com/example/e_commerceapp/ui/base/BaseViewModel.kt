package com.example.e_commerceapp.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.data.local.db.DatabaseDataSource
import com.example.e_commerceapp.data.remote.FirebaseDataSource
import com.example.e_commerceapp.data.remote.FirebaseRepository

abstract class BaseViewModel(private val firebaseRepository: FirebaseDataSource, private val databaseRepository: DatabaseDataSource, private val savedStateHandle : SavedStateHandle) : ViewModel()  {

    val isLoading = ObservableBoolean()

    fun getFirebaseRepository() : FirebaseDataSource = firebaseRepository
    fun getDatabaseRepository() : DatabaseDataSource = databaseRepository

    fun getSaveStateHandle() : SavedStateHandle = savedStateHandle

    fun setIsLoading(b:Boolean){
        isLoading.set(b)
    }
}