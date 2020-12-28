package com.example.e_commerceapp.data.model

import java.io.Serializable

data class ShopCartProduct(
    val id : Int,
    val productID: Int,
    val customerID: Int
) : Serializable {
}