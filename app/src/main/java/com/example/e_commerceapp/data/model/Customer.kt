package com.example.e_commerceapp.data.model

import java.io.Serializable

data class Customer(
    val id  : Int = 0,
    val username : String = "",
    val password : String = "",
    val gender : String = " ",
    val birthday : String = "",
    val job : String = "",
) : Serializable