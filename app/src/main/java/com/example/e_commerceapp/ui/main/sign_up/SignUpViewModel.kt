package com.example.e_commerceapp.ui.main.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.service.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SignUpViewModel (firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {

    private val validSignUpData = MutableLiveData<Boolean>()
    val validSignUpLiveData : LiveData<Boolean> get() = validSignUpData

    fun signUpNewCustomer(customer: Customer){
        // check for same username taken
        getFirebaseRepository().getCustomerDataQuery(customer.username).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // this username is already taken
                    validSignUpData.value = false
                }else{
                    // if not push to firebase
                    getFirebaseRepository().pushCustomerToFirebase(customer).addOnSuccessListener {
                        validSignUpData.value = true
                    }.addOnFailureListener{
                        validSignUpData.value = false
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                validSignUpData.value = false
            }


        })

    }

}