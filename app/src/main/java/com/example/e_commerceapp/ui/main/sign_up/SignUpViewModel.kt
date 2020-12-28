package com.example.e_commerceapp.ui.main.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.service.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel

class SignUpViewModel (firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {

    private val validSignUpData = MutableLiveData<Boolean>()
    val validSignUpLiveData : LiveData<Boolean> get() = validSignUpData

    fun signUpNewCustomer(customer: Customer){
        // check for same username taken
        // if not push to firebase
        getFirebaseRepository().pushCustomerToFirebase(customer).addOnSuccessListener {
            validSignUpData.value = true
        }.addOnFailureListener{
            validSignUpData.value = false
        }
    }

}