package com.example.e_commerceapp.data.model

import java.io.Serializable

data class Product(
    val productID : Int,
    val productName : String,
    val productQuantity : Int,
    val productPrice : Double,
) : Serializable