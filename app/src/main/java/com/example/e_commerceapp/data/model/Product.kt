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
    val id : Int = 0,
    val name : String = "",
    val quantity : Int = 0,
    val price : Double = 0.0,
    val image : String = "",
    val description : String = "",
    val categoryId : String = ""
) : Serializable {

}