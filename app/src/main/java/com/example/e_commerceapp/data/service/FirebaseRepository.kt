package com.example.e_commerceapp.data.service

import android.net.Uri
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.utils.AppConstants
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask

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

    override fun pushProductToFirebase(product: Product) {
        databaseReference.child(AppConstants.CATEGORIES_REF)
            .child(product.categoryId)
            .push()
            .setValue(product)
    }

    override fun getProductsDataQuery(categoryName: String): Query {
        return databaseReference.child(AppConstants.CATEGORIES_REF)
            .child(categoryName)
    }


}