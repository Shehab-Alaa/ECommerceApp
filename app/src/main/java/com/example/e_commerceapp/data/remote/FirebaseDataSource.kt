package com.example.e_commerceapp.data.remote

import com.google.firebase.database.Query

interface FirebaseDataSource {

    fun getCustomerDataQuery(username:String) : Query
}