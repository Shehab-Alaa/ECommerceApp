package com.example.e_commerceapp.ui.main.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.model.Order
import com.example.e_commerceapp.data.service.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel
import com.example.e_commerceapp.utils.AppConstants

class OrderViewModel(firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {

    private val orderData = MutableLiveData<Order>(getSaveStateHandle().get(AppConstants.SELECTED_ORDER))
    val orderLiveData : LiveData<Order> get() = orderData

    fun deleteCustomerShopCart(customerUsername : String){
        getFirebaseRepository().deleteCustomerShopCart(customerUsername)
    }

    fun addCustomerOrder(customerUsername: String){
        orderLiveData.value?.let { getFirebaseRepository().pushCustomerOrder(customerUsername, it) }
    }

}