package com.example.e_commerceapp.data.service

import android.net.Uri
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.model.Order
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.utils.AppConstants
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlin.math.log

class FirebaseRepository(private val databaseReference : DatabaseReference) : FirebaseDataSource {

    override fun pushCustomerToFirebase(customer: Customer): Task<Void> {
        return databaseReference.child(AppConstants.CUSTOMERS_REF)
            .push()
            .setValue(customer)
    }

    override fun getCustomerDataQuery(username:String) : Query {
        return databaseReference.child(AppConstants.CUSTOMERS_REF)
            .orderByChild(AppConstants.USERNAME_REF)
            .equalTo(username)
    }

    override fun getUploadImageFirebaseTask(imageUri: Uri): UploadTask {
        return FirebaseStorage.getInstance()
            .getReference(AppConstants.PRODUCTS_IMAGES + System.currentTimeMillis() + ".png")
            .putFile(imageUri)
    }

    override fun pushProductToFirebase(product: Product,categoryName: String) {
        databaseReference.child(AppConstants.CATEGORIES_REF)
            .child(categoryName)
            .push()
            .setValue(product)
    }

    override fun getProductsDataQuery(categoryName: String): Query {
        return databaseReference.child(AppConstants.CATEGORIES_REF)
            .child(categoryName)
    }

    override fun pushShopCartProduct(customerUsername: String, shopCartProduct: Product) {
        databaseReference.child(AppConstants.SHOP_CART_PRODUCTS_REF)
            .child(customerUsername)
            .push()
            .setValue(shopCartProduct)
    }

    override fun getShopCartProductsDataQuery(loginCustomerUsername: String): Query {
        return databaseReference.child(AppConstants.SHOP_CART_PRODUCTS_REF)
            .child(loginCustomerUsername)
    }

    override fun getProductKeyQuery(loginCustomerUsername: String, productName: String): Query {
        return databaseReference.child(AppConstants.SHOP_CART_PRODUCTS_REF)
            .child(loginCustomerUsername)
            .orderByChild(AppConstants.PRODUCT_NAME_REF)
            .equalTo(productName)
    }

    override fun updateProductQuantity(loginCustomerUsername: String, productKey: String, productQuantity: Int) {
        databaseReference.child(AppConstants.SHOP_CART_PRODUCTS_REF)
            .child(loginCustomerUsername)
            .child(productKey)
            .child(AppConstants.PRODUCT_QUANTITY_REF).setValue(productQuantity)
    }

    override fun deleteShopCartProduct(loginCustomerUsername: String, productKey: String) {
        databaseReference.child(AppConstants.SHOP_CART_PRODUCTS_REF)
            .child(loginCustomerUsername)
            .child(productKey).removeValue()
    }

    override fun deleteCustomerShopCart(loginCustomerUsername: String) {
        databaseReference.child(AppConstants.SHOP_CART_PRODUCTS_REF)
            .child(loginCustomerUsername)
            .removeValue()
    }

    override fun pushCustomerOrder(customerUsername: String, order: Order) {
        databaseReference.child(AppConstants.ORDERS_REF)
            .child(customerUsername)
            .push()
            .setValue(order)
    }

    override fun getCustomerOrdersQuery(customerUsername: String) : Query {
        return databaseReference.child(AppConstants.ORDERS_REF)
            .child(customerUsername)
    }


}