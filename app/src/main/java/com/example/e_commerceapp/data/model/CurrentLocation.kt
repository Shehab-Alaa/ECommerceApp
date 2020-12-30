package com.example.e_commerceapp.data.model

import java.io.Serializable

data class CurrentLocation(
    val latitude : Double = 0.0 ,
    val longitude : Double = 0.0
) : Serializable