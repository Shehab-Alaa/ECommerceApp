package com.example.e_commerceapp.ui.main.order

import androidx.databinding.ObservableField
import com.example.e_commerceapp.data.model.Product

class OrderItemViewModel(product: Product){

    val productImage: ObservableField<String> = ObservableField<String>(product.image)
    val productName: ObservableField<String> = ObservableField<String>(product.name)
    val productQuantity: ObservableField<String> = ObservableField<String>(product.quantity.toString())

}