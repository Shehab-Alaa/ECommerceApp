package com.example.e_commerceapp.ui.main.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.service.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class PasswordRecoveryViewModel(firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {

    private val customerData = MutableLiveData<Customer>()
    val customerLiveData : LiveData<Customer> get() = customerData

    fun getCustomerData(customerUsername:String , customerJob:String){
        getFirebaseRepository().getCustomerDataQuery(customerUsername).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val customer: Customer? = snapshot.children.iterator().next().getValue(Customer::class.java)
                    if (customer?.job.equals(customerJob)){
                        customerData.value = customer
                    }else {
                        customerData.value = null
                    }
                }else{
                    customerData.value = null
                }
            }
            override fun onCancelled(error: DatabaseError) {
                customerData.value = null
            }
        })
    }


}