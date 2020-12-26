package com.example.e_commerceapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.e_commerceapp.data.local.db.dao.CategoriesDAO
import com.example.e_commerceapp.data.local.db.dao.ProductsDAO
import com.example.e_commerceapp.data.local.db.dao.ShopCartProductsDAO
import com.example.e_commerceapp.data.model.Category
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.data.model.ShopCartProduct

@Database(entities = [Category::class , Product::class , ShopCartProduct::class] , version = 1 , exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val categoriesDAO : CategoriesDAO
    abstract val productsDAO : ProductsDAO
    abstract val shopCartProductsDAO : ShopCartProductsDAO
}