package com.example.e_commerceapp.ui.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.data.service.FirebaseDataSource

abstract class BaseViewModel(private val firebaseRepository: FirebaseDataSource, private val savedStateHandle : SavedStateHandle) : ViewModel()  {

    fun getFirebaseRepository() : FirebaseDataSource = firebaseRepository

    fun getSaveStateHandle() : SavedStateHandle = savedStateHandle

}