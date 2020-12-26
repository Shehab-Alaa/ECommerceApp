package com.example.e_commerceapp.data.model

import java.io.Serializable

data class Order(
    val orderID : Int,
    val products : List<Product>,
    val customerID : Int,
    val quantity : Int ,
    val totalPrice : Double
) : Serializable {
}