package com.example.e_commerceapp.data.remote

import com.google.firebase.database.DatabaseReference

class FirebaseRepository(private val databaseReference : DatabaseReference) : FirebaseDataSource {


    override fun updateCustomer(){
        databaseReference.child("customers").child("one").child("Username").setValue("Shehab Alaa")
    }
}