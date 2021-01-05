package com.example.e_commerceapp.ui.main.customer_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.service.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel
import com.example.e_commerceapp.utils.AppConstants

class CustomerProfileViewModel (firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {

    private val customerData : MutableLiveData<Customer> = MutableLiveData(getSaveStateHandle().get(AppConstants.LOGIN_CUSTOMER))
    val customerLiveData : LiveData<Customer> get() = customerData

}