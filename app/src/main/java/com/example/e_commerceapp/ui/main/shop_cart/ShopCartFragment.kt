package com.example.e_commerceapp.ui.main.shop_cart

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.databinding.FragmentShopCartBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import com.example.e_commerceapp.ui.main.HomeActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShopCartFragment:BaseFragment<FragmentShopCartBinding , ShopCartViewModel>(),
    ShopCartProductsAdapter.CartProductsAdapterListener {

    private val shopCartViewModel : ShopCartViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }
    private val loginCustomer = (activity as HomeActivity).getLoginCustomer()
    private val cartProductsAdapter = ShopCartProductsAdapter(mutableListOf() , this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initRecipesRecyclerView()
    }

    private fun initRecipesRecyclerView(){
        getViewDataBinding().cartProductsRv.layoutManager = LinearLayoutManager(context)
        getViewDataBinding().cartProductsRv.setHasFixedSize(true)
        // set Animation to all children (items) of this Layout
        getViewDataBinding().cartProductsRv.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        getViewDataBinding().cartProductsRv.adapter = cartProductsAdapter
    }

    override fun incProductQuantity(view: View, product: Product) {
        TODO("Not yet implemented")
    }

    override fun decProductQuantity(view: View, product: Product) {
        TODO("Not yet implemented")
    }

    override fun deleteProductFromCart(view: View, product: Product) {
        TODO("Not yet implemented")
    }

    override val layoutId: Int
        get() = R.layout.fragment_shop_cart
    override val bindingVariable: Int
        get() = BR.shopCartViewModel

    override fun getViewModel(): ShopCartViewModel {
        return shopCartViewModel
    }

}