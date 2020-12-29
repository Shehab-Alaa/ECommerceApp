package com.example.e_commerceapp.ui.main.shop_cart

import android.view.View
import androidx.databinding.ObservableField
import com.example.e_commerceapp.data.model.Product

class ShopCartItemViewModel (private val product: Product,
                             private val deleteProductCartClickListener: DeleteProductCartClickListener,
                             private val changeQuantityClickListener: ChangeQuantityClickListener) {

    val productImage: ObservableField<String> = ObservableField<String>(product.image)
    val productName: ObservableField<String> = ObservableField<String>(product.name)
    val productPrice: ObservableField<String> = ObservableField<String>(product.price.toString() + " EGP")
    val productQuantity: ObservableField<String> = ObservableField<String>(product.quantity.toString())


    fun deleteProductFromCart(view: View){
        deleteProductCartClickListener.deleteProductFromCart(view,product)
    }

    fun incProductQuantity(view: View){
        product.quantity += 1
        productQuantity.set(product.quantity.toString())
        changeQuantityClickListener.incProductQuantity(view, product)
    }

    fun decProductQuantity(view: View){
        product.quantity -= 1
        productQuantity.set(product.quantity.toString())
        changeQuantityClickListener.decProductQuantity(view, product)
    }

    interface DeleteProductCartClickListener{
        fun deleteProductFromCart(view: View, product: Product)
    }

    interface ChangeQuantityClickListener{
        fun incProductQuantity(view: View,product: Product)
        fun decProductQuantity(view: View,product: Product)
    }
}