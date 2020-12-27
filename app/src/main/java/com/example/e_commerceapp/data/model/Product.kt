package com.example.e_commerceapp.data.model

import android.net.Uri
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.e_commerceapp.utils.AppConstants
import java.io.Serializable

@Entity(tableName = AppConstants.PRODUCTS_TABLE_NAME)
data class Product(
    @PrimaryKey (autoGenerate = true)
    @NonNull
    val id : Int,
    val name : String,
    val quantity : Int,
    val price : Double,
    val image : Uri,
    val categoryId : String
) : Serializable