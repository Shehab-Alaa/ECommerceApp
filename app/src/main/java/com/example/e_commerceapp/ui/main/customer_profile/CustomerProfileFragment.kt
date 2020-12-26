package com.example.e_commerceapp.ui.main.customer_profile

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentCustomerProfileBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CustomerProfileFragment:BaseFragment<FragmentCustomerProfileBinding , CustomerProfileViewModel>() {

    private val customerProfileViewModel : CustomerProfileViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }

    override val layoutId: Int
        get() = R.layout.fragment_customer_profile
    override val bindingVariable: Int
        get() = BR.customerProfileViewModel

    override fun getViewModel(): CustomerProfileViewModel {
        return customerProfileViewModel
    }
}