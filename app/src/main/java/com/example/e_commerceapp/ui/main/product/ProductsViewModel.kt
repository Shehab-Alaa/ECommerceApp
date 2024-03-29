package com.example.e_commerceapp.ui.main.product

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
import java.util.Locale.ROOT

class ProductsViewModel (firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {

    private val categoryName : String = getSaveStateHandle().get(AppConstants.SELECTED_CATEGORY)!!

    // observed by view => that change source between (actual) data and (filtered) data according to customer interactions.
    private val filteredProductsData = MutableLiveData<MutableList<Product>>()
    val filteredProductsLiveData : LiveData<MutableList<Product>> = filteredProductsData

    private val productsData = MutableLiveData<MutableList<Product>>()
    val productsLiveData : LiveData<MutableList<Product>> = productsData

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

    fun searchProductsData(searchInput : String){

        val productsHolder = mutableListOf<Product>()
        productsData.value?.let {
            if (it.isNotEmpty()){
                for (product in it){
                    if (product.name.toLowerCase(ROOT).contains(searchInput.toLowerCase(ROOT))) {
                        productsHolder.add(product)
                    }
                }
            }
        }
        filteredProductsData.value = productsHolder
    }

    fun addShopCartProduct(customerUsername : String, shopCartProduct : Product){
        getFirebaseRepository().pushShopCartProduct(customerUsername , shopCartProduct)
    }

}