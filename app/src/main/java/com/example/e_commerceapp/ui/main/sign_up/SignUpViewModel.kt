package com.example.e_commerceapp.ui.main.sign_up

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.local.db.DatabaseDataSource
import com.example.e_commerceapp.data.remote.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel

class SignUpViewModel (firebaseRepository: FirebaseDataSource, databaseRepository: DatabaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,databaseRepository,saveStateHandle) {
}