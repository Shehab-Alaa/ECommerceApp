package com.example.e_commerceapp.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.e_commerceapp.utils.AppConstants
import java.io.Serializable

@Entity (tableName = AppConstants.SHOP_CART_PRODUCTS_TABLE_NAME)
data class ShopCartProduct(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id : Int,
    val product: Product,
    val customerID: Int
) : Serializable {
}