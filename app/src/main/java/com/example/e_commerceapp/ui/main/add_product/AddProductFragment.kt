package com.example.e_commerceapp.ui.main.add_product

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentAddProductBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AddProductFragment:BaseFragment<FragmentAddProductBinding , AddProductViewModel>() {

    private val addProductViewModel: AddProductViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }

    override val layoutId: Int
        get() = R.layout.fragment_add_product
    override val bindingVariable: Int
        get() = BR.addProductViewModel

    override fun getViewModel(): AddProductViewModel {
        return addProductViewModel
    }

}