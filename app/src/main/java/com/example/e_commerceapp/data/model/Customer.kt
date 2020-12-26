package com.example.e_commerceapp.data.model

import java.io.Serializable

data class Customer(
    val customerID : Int,
    val customerUserName : String,
    val customerPassword : String,
    val customerGender : String,
    val customerBirthday : String,
    val customerJob : String,
) : Serializable