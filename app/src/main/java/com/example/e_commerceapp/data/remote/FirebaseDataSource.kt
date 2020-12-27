package com.example.e_commerceapp.data.remote

import android.net.Uri
import com.example.e_commerceapp.data.model.Product
import com.google.firebase.database.Query
import com.google.firebase.storage.UploadTask

interface FirebaseDataSource {

    fun getCustomerDataQuery(username:String) : Query
    fun getUploadImageFirebaseTask(imageUri : Uri) : UploadTask
    fun pushProductToFirebase(product: Product)
    fun getProductsDataQuery(categoryName : String) : Query
}