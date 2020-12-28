package com.example.e_commerceapp.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.data.remote.FirebaseDataSource
import com.example.e_commerceapp.data.remote.FirebaseRepository

abstract class BaseViewModel(private val firebaseRepository: FirebaseDataSource, private val savedStateHandle : SavedStateHandle) : ViewModel()  {

    fun getFirebaseRepository() : FirebaseDataSource = firebaseRepository

    fun getSaveStateHandle() : SavedStateHandle = savedStateHandle

}