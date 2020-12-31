package com.example.e_commerceapp.ui.main.orders_history

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.databinding.FragmentOrdersHistoryBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import com.example.e_commerceapp.ui.main.HomeActivity
import com.example.e_commerceapp.ui.main.customer_profile.CustomerProfileFragmentDirections
import com.example.e_commerceapp.ui.main.product.ProductsFragmentDirections
import com.example.e_commerceapp.utils.AppConstants
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class OrdersHistoryFragment:BaseFragment<FragmentOrdersHistoryBinding , OrdersHistoryViewModel>() {

    private lateinit var ordersHistoryViewModel : OrdersHistoryViewModel
    private lateinit var loginCustomer : Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginCustomer = (activity as HomeActivity).getLoginCustomer()
        ordersHistoryViewModel = getViewModel { parametersOf(SavedStateHandle(mapOf(AppConstants.LOGIN_CUSTOMER to loginCustomer.username))) }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_shop_cart)
            getNavController().navigate(CustomerProfileFragmentDirections.actionCustomerProfileItemToShopCartItem())
        return super.onOptionsItemSelected(item)
    }

    override val layoutId: Int
        get() = R.layout.fragment_orders_history
    override val bindingVariable: Int
        get() = BR.ordersHistoryViewModel

    override fun getViewModel(): OrdersHistoryViewModel {
        return ordersHistoryViewModel
    }
}