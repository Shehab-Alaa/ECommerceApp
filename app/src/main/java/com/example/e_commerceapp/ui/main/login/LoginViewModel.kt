package com.example.e_commerceapp.ui.main.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.remote.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class LoginViewModel (firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {

    val rememberMeLiveData : MutableLiveData<Boolean> = MutableLiveData()

    private val validLogin = MutableLiveData<Boolean>()
    val validLoginLiveData : LiveData<Boolean> get() = validLogin

    private val customerData = MutableLiveData<Customer>()
    val customerLiveData : LiveData<Customer> get() = customerData

    fun fetchCustomerData(username:String, password:String){
        validLogin.value = true
        getFirebaseRepository().getCustomerDataQuery(username).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    val customer: Customer? = snapshot.children.iterator().next().getValue(Customer::class.java)
                    if (customer?.password.equals(password)){
                       customerData.value = customer
                    }else {
                        validLogin.value = false
                    }
                }else{
                    validLogin.value = false
                }

            }

            override fun onCancelled(error: DatabaseError) {
                validLogin.value = false
            }


        })
    }

    fun onForgotPasswordClick(view: View){

    }

    fun onSignUpClick(view:View){

    }

}