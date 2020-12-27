package com.example.e_commerceapp.ui.main.product

import android.view.View
import androidx.databinding.ObservableField
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.ui.base.BaseItemListener

class ProductItemViewModel(private val product: Product,
                           private val productItemClickListener: ProductItemClickListener ,
                           private val addToCartClickListener: AddToCartClickListener) {

    val productImage: ObservableField<String> = ObservableField<String>(product.image)
    val productName: ObservableField<String> = ObservableField<String>(product.name)
    val productPrice: ObservableField<String> = ObservableField<String>(product.price.toString() + " EGP")

    fun onItemClick(view: View) {
        productItemClickListener.onItemClick(view, product)
    }

    fun onAddItemToCartClick(view: View){
        addToCartClickListener.onAddToCartClick(view,product)
    }

    interface ProductItemClickListener : BaseItemListener<Product>  // to be implemented by the adapter

    interface AddToCartClickListener {
        fun onAddToCartClick(view: View , product: Product)
    }
}