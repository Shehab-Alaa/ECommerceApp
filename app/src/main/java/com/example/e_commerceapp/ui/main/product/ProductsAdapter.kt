package com.example.e_commerceapp.ui.main.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.databinding.ItemProductBinding
import com.example.e_commerceapp.ui.base.BaseItemListener
import com.example.e_commerceapp.ui.base.BaseRecyclerViewAdapter
import com.example.e_commerceapp.ui.base.BaseViewHolder

class ProductsAdapter(productItems : MutableList<Product>, private val productsAdapterListener: ProductsAdapterListener) : BaseRecyclerViewAdapter<Product>(productItems) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return  ProductsViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    interface ProductsAdapterListener : BaseItemListener<Product> , ProductItemViewModel.AddToCartClickListener

    inner class ProductsViewHolder(private val itemProductBinding: ItemProductBinding) : BaseViewHolder(itemProductBinding.root), ProductItemViewModel.ProductItemClickListener,
        ProductItemViewModel.AddToCartClickListener {

        override fun onBind(position: Int) {
            itemProductBinding.productItemViewModel = ProductItemViewModel(getItems()[position], this , this)
            itemProductBinding.executePendingBindings()
        }

        override fun onItemClick(view: View, item: Product) {
            productsAdapterListener.onItemClick(view, item)
        }

        override fun onAddToCartClick(view: View, product: Product) {
            productsAdapterListener.onAddToCartClick(view, product)
        }
    }

}