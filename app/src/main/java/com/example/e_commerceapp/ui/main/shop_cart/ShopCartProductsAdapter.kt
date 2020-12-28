package com.example.e_commerceapp.ui.main.shop_cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.databinding.ItemShopCartProductBinding
import com.example.e_commerceapp.ui.base.BaseRecyclerViewAdapter
import com.example.e_commerceapp.ui.base.BaseViewHolder

class ShopCartProductsAdapter(cartProductItems: MutableList<Product> , private val cartProductsAdapterListener: CartProductsAdapterListener) : BaseRecyclerViewAdapter<Product>(cartProductItems) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return  CartProductsViewHolder(ItemShopCartProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    interface CartProductsAdapterListener : ShopCartItemViewModel.ChangeQuantityClickListener ,  ShopCartItemViewModel.DeleteProductCartClickListener

    inner class CartProductsViewHolder(private val itemShopCartProductBinding: ItemShopCartProductBinding) : BaseViewHolder(itemShopCartProductBinding.root),
        ShopCartItemViewModel.DeleteProductCartClickListener,
        ShopCartItemViewModel.ChangeQuantityClickListener {
        override fun onBind(position: Int) {
            itemShopCartProductBinding.shopCartItemViewModel = ShopCartItemViewModel(getItems()[position], this , this)
            itemShopCartProductBinding.executePendingBindings()
        }

        override fun deleteProductFromCart(view: View, product: Product) {
            cartProductsAdapterListener.deleteProductFromCart(view, product)
        }

        override fun incProductQuantity(view: View, product: Product) {
            cartProductsAdapterListener.incProductQuantity(view, product)
        }

        override fun decProductQuantity(view: View, product: Product) {
            cartProductsAdapterListener.decProductQuantity(view, product)
        }

    }

}