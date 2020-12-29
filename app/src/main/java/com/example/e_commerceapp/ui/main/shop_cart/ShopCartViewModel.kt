package com.example.e_commerceapp.ui.main.shop_cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.data.service.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel
import com.example.e_commerceapp.utils.AppConstants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ShopCartViewModel(firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {

    private val loginCustomerUsername = getSaveStateHandle().get<String>(AppConstants.LOGIN_CUSTOMER)

    private val cartProductsData = MutableLiveData<MutableList<Product>>()
    val cartProductsLiveData : LiveData<MutableList<Product>> get() = cartProductsData

    init {
        getShopCartProductsData()
    }

    private fun getShopCartProductsData(){
        getFirebaseRepository().getShopCartProductsDataQuery(loginCustomerUsername.toString())
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val productsHolder = mutableListOf<Product>()
                    snapshot.children.forEach {
                        it.getValue(Product::class.java)?.let { it1 -> productsHolder.add(it1) }
                    }
                    cartProductsData.value = productsHolder
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("Here" , "Cancelled")
                }

            })
    }


}