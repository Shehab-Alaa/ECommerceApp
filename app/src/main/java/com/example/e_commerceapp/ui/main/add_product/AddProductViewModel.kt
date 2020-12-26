package com.example.e_commerceapp.ui.main.add_product

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.local.db.DbRepositorySource
import com.example.e_commerceapp.ui.base.BaseViewModel

class AddProductViewModel(private val databaseRepository: DbRepositorySource, saveStateHandle: SavedStateHandle) : BaseViewModel(databaseRepository,saveStateHandle) {
}