package com.example.e_commerceapp.ui.main.product

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentProductsBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductsFragment:BaseFragment<FragmentProductsBinding , ProductsViewModel>() {

    private val productsViewModel : ProductsViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }

    override val layoutId: Int
        get() = R.layout.fragment_products
    override val bindingVariable: Int
        get() = BR.productsViewModel

    override fun getViewModel(): ProductsViewModel {
        return productsViewModel
    }
}