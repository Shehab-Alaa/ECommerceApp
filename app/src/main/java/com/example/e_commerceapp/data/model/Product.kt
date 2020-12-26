package com.example.e_commerceapp.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.e_commerceapp.utils.AppConstants
import java.io.Serializable

@Entity(tableName = AppConstants.PRODUCTS_TABLE_NAME)
data class Product(
    @PrimaryKey (autoGenerate = true)
    @NonNull
    val productID : Int,
    val productName : String,
    val productQuantity : Int,
    val productPrice : Double,
    val productImage : String,
    val categoryID : Int
) : Serializable