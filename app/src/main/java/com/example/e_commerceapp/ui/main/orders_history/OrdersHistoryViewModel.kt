package com.example.e_commerceapp.ui.main.orders_history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.model.Order
import com.example.e_commerceapp.data.service.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel
import com.example.e_commerceapp.utils.AppConstants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class OrdersHistoryViewModel (firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {

    private val loginCustomerUsername = getSaveStateHandle().get<String>(AppConstants.LOGIN_CUSTOMER)

    private val ordersData = MutableLiveData<MutableList<Order>>()
    val ordersLiveData : LiveData<MutableList<Order>> get() = ordersData

    init {
        getCustomerOrders()
    }

    private fun getCustomerOrders(){
        getFirebaseRepository().getCustomerOrdersQuery(loginCustomerUsername.toString()).addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val ordersHolder = mutableListOf<Order>()
                snapshot.children.forEach {
                    it.getValue(Order::class.java)?.let { it1 -> ordersHolder.add(it1) }
                }
                ordersData.value = ordersHolder
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Here" , "Cancelled")
            }

        })
    }

}