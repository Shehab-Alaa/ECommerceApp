package com.example.e_commerceapp.utils

import com.example.e_commerceapp.BuildConfig

object AppConstants {
    const val CATEGORIES_TABLE_NAME = "categories"
    const val PRODUCTS_TABLE_NAME = "products"
    const val SHOP_CART_PRODUCTS_TABLE_NAME = "shop_cart_products"

    const val DATABASE_NAME = "e_commerce_database"

    const val CUSTOMERS_REF = "customers"
    const val USERNAME_REF = "username"
    const val PASSWORD_REF = "password"

    const val LOGIN_CUSTOMER = "login_customer"

    const val CATEGORIES_REF = "categories"
    const val PRODUCTS_IMAGES = "productsImages/"

    const val SELECTED_CATEGORY = "categoryName"

    const val PREF_NAME = BuildConfig.APPLICATION_ID + "_pref"
    const val REMEMBERED_CUSTOMER = "rememberMe"

    const val SHOP_CART_PRODUCTS_REF = "shopCartProducts"
}