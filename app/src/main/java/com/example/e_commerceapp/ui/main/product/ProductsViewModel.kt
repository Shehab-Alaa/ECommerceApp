package com.example.e_commerceapp.ui.main.product

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.local.db.DatabaseDataSource
import com.example.e_commerceapp.data.remote.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel

class ProductsViewModel (firebaseRepository: FirebaseDataSource, databaseRepository: DatabaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,databaseRepository,saveStateHandle) {
}