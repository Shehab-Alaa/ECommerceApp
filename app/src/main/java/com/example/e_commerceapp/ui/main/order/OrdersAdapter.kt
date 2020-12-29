package com.example.e_commerceapp.ui.main.order

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.databinding.ItemOrderBinding
import com.example.e_commerceapp.ui.base.BaseRecyclerViewAdapter
import com.example.e_commerceapp.ui.base.BaseViewHolder

class OrdersAdapter(productItems : MutableList<Product>) : BaseRecyclerViewAdapter<Product>(productItems) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return  ProductsViewHolder(ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class ProductsViewHolder(private val itemOrderBinding: ItemOrderBinding) : BaseViewHolder(itemOrderBinding.root) {

        override fun onBind(position: Int) {
            itemOrderBinding.orderItemViewModel = OrderItemViewModel(getItems()[position])
            itemOrderBinding.executePendingBindings()
        }
    }

}