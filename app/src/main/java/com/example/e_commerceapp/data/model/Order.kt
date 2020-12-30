package com.example.e_commerceapp.data.model

import android.location.Location
import java.io.Serializable

data class Order(
    val customerUsername: String = "",
    val products: List<Product> = mutableListOf(),
    var deliveryLocation: Location? = null,
    var orderDate: String = "",
    val totalPrice: Double = 0.0
) : Serializable {
}