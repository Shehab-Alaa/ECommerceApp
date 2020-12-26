package com.example.e_commerceapp.ui.main.shop_cart

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentShopCartBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShopCartFragment:BaseFragment<FragmentShopCartBinding , ShopCartViewModel>() {

    private val shopCartViewModel : ShopCartViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }

    override val layoutId: Int
        get() = R.layout.fragment_shop_cart
    override val bindingVariable: Int
        get() = BR.shopCartViewModel

    override fun getViewModel(): ShopCartViewModel {
        return shopCartViewModel
    }
}