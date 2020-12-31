package com.example.e_commerceapp.ui.main.product_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.data.service.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel
import com.example.e_commerceapp.utils.AppConstants

class ProductDetailsViewModel(firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {

    val productLiveData : LiveData<Product> = MutableLiveData(getSaveStateHandle().get(AppConstants.SELECTED_PRODUCT))

}