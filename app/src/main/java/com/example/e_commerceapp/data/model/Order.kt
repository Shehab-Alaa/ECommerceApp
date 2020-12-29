package com.example.e_commerceapp.data.model

import java.io.Serializable

data class Order(
    val customerUsername : String = "",
    val products : List<Product> = mutableListOf(),
    var deliveryLocation : String = "",
    var orderDate : String = "" ,
    val totalPrice : Double = 0.0
) : Serializable {
}