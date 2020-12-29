package com.example.e_commerceapp.ui.main.order

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentOrderBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class OrderFragment:BaseFragment<FragmentOrderBinding , OrderViewModel>() {

    private val orderViewModel : OrderViewModel by viewModel { parametersOf(SavedStateHandle()) }

    // pass username and productsList
    // check total price by price * quantity of each product
    // get user location on map and save it
    // change classes to match firebase database
    // push orders to username child in orders child

    override val layoutId: Int
        get() = R.layout.fragment_order
    override val bindingVariable: Int
        get() = BR.orderViewModel

    override fun getViewModel(): OrderViewModel {
       return orderViewModel
    }
}