package com.example.e_commerceapp.data.service

import android.net.Uri
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.model.Product
import com.google.android.gms.tasks.Task
import com.google.firebase.database.Query
import com.google.firebase.storage.UploadTask

interface FirebaseDataSource {

    fun pushCustomerToFirebase(customer : Customer): Task<Void>
    fun getCustomerDataQuery(username:String) : Query
    fun getUploadImageFirebaseTask(imageUri : Uri) : UploadTask
    fun pushProductToFirebase(product: Product)
    fun getProductsDataQuery(categoryName : String) : Query
    fun pushShopCartProduct(customerUsername : String , shopCartProduct: Product)
    fun getShopCartProductsDataQuery(loginCustomerUsername : String) : Query
}