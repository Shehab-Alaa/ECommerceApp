package com.example.e_commerceapp.data.service

import android.net.Uri
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.model.Order
import com.example.e_commerceapp.data.model.Product
import com.google.android.gms.tasks.Task
import com.google.firebase.database.Query
import com.google.firebase.storage.UploadTask

interface FirebaseDataSource {

    fun pushCustomerToFirebase(customer : Customer): Task<Void>
    fun getCustomerDataQuery(username:String) : Query
    fun getUploadImageFirebaseTask(imageUri : Uri) : UploadTask
    fun pushProductToFirebase(product: Product,categoryName: String)
    fun getProductsDataQuery(categoryName : String) : Query
    fun pushShopCartProduct(customerUsername : String , shopCartProduct: Product)
    fun getShopCartProductsDataQuery(loginCustomerUsername : String) : Query
    fun getProductKeyQuery(loginCustomerUsername: String , productName : String): Query
    fun updateProductQuantity(loginCustomerUsername: String , productKey : String , productQuantity: Int)
    fun deleteShopCartProduct(loginCustomerUsername: String , productKey: String)
    fun deleteCustomerShopCart(loginCustomerUsername: String)
    fun pushCustomerOrder(customerUsername: String,order: Order)

}