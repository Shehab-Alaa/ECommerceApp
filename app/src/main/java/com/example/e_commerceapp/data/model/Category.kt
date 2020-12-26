package com.example.e_commerceapp.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.e_commerceapp.utils.AppConstants
import java.io.Serializable

@Entity (tableName = AppConstants.CATEGORIES_TABLE_NAME)
data class Category(
    @PrimaryKey (autoGenerate = true)
    @NonNull
    val categoryID : Int ,
    val categoryName : String,
    val categoryProducts : List<Product>
) : Serializable {
}