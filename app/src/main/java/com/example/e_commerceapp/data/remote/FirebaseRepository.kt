package com.example.e_commerceapp.data.remote

import com.example.e_commerceapp.utils.AppConstants
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query

class FirebaseRepository(private val databaseReference : DatabaseReference) : FirebaseDataSource {


     override fun getCustomerDataQuery(username:String) : Query {
        return databaseReference.child(AppConstants.CUSTOMERS_REF)
            .orderByChild(AppConstants.USERNAME_REF)
            .equalTo(username)
    }
}