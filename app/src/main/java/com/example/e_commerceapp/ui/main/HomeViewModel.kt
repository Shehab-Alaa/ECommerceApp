package com.example.e_commerceapp.ui.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.service.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel

class HomeViewModel(firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {
}