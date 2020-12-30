package com.example.e_commerceapp.data.model

import java.io.Serializable

data class Product(
    val name : String = "",
    var quantity : Int = 0,
    val price : Double = 0.0,
    val image : String = "",
    val description : String = "",
) : Serializable {

}