package com.example.e_commerceapp.ui.main.product_details

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentProductDetailsBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailsFragment:BaseFragment<FragmentProductDetailsBinding , ProductDetailsViewModel>() {

    private val productDetailsViewModel : ProductDetailsViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }

    override val layoutId: Int
        get() = R.layout.fragment_product_details
    override val bindingVariable: Int
        get() = BR.productDetailsViewModel

    override fun getViewModel(): ProductDetailsViewModel {
        return productDetailsViewModel
    }
}