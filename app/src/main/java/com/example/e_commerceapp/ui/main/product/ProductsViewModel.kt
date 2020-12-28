package com.example.e_commerceapp.ui.main.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.data.remote.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel
import com.example.e_commerceapp.utils.AppConstants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ProductsViewModel (firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {

    private val categoryName : String = getSaveStateHandle().get(AppConstants.SELECTED_CATEGORY)!!

    private val productsData = MutableLiveData<MutableList<Product>>()
    val productsLiveData : LiveData<MutableList<Product>> get() = productsData

    init {
        getProductsData()
    }

    private fun getProductsData(){
        getFirebaseRepository().getProductsDataQuery(categoryName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val productsHolder = mutableListOf<Product>()
                    snapshot.children.forEach {
                        it.getValue(Product::class.java)?.let { it1 -> productsHolder.add(it1) }
                    }
                    productsData.value = productsHolder
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    fun filterProductsData(searchInput : String){
        productsData.postValue(productsData.value?.filter { it.name.contains(searchInput) } as MutableList<Product>?)
    }

}