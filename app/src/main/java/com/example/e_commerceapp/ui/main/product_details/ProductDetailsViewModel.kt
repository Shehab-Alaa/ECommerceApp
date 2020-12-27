package com.example.e_commerceapp.ui.main.product_details

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.local.db.DatabaseDataSource
import com.example.e_commerceapp.data.remote.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel

class ProductDetailsViewModel(firebaseRepository: FirebaseDataSource, databaseRepository: DatabaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,databaseRepository,saveStateHandle) {
}