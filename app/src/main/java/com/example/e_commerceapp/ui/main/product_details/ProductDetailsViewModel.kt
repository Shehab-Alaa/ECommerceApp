package com.example.e_commerceapp.ui.main.product_details

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.local.db.DbRepositorySource
import com.example.e_commerceapp.ui.base.BaseViewModel

class ProductDetailsViewModel (private val databaseRepository: DbRepositorySource, saveStateHandle: SavedStateHandle) : BaseViewModel(databaseRepository,saveStateHandle) {
}