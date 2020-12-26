package com.example.e_commerceapp.ui.main.product

import android.view.View
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.ui.base.BaseItemListener

class ProductItemViewModel(private val product: Product,
                           private val productItemClickListener: ProductItemClickListener ) {

    fun onItemClick(view: View) {
        productItemClickListener.onItemClick(view, product)
    }

    interface ProductItemClickListener : BaseItemListener<Product>  // to be implemented by the adapter

}