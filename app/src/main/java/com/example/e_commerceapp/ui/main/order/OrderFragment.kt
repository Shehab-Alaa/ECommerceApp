package com.example.e_commerceapp.ui.main.order

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.databinding.FragmentOrderBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import com.example.e_commerceapp.ui.main.HomeActivity
import com.example.e_commerceapp.ui.main.product.ProductsFragmentArgs
import com.example.e_commerceapp.utils.AppConstants
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.math.log

class OrderFragment:BaseFragment<FragmentOrderBinding , OrderViewModel>() {

    private lateinit var orderViewModel : OrderViewModel
    private val ordersAdapter = OrdersAdapter(mutableListOf())
    private lateinit var loginCustomer: Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = OrderFragmentArgs.fromBundle(requireArguments())
        orderViewModel = getViewModel{ parametersOf(SavedStateHandle(mapOf(AppConstants.SELECTED_ORDER to args.selectedOrder))) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginCustomer = (activity as HomeActivity).getLoginCustomer()

        setHasOptionsMenu(false)
        initRecipesRecyclerView()


        getViewDataBinding().confirmOrderBtn.setOnClickListener {
            // push this order to customer username child in orders ref
            // delete data from shopCartProducts of this customer username
            // navigate back to home products
            Toast.makeText(context,"order is confirmed" , Toast.LENGTH_SHORT).show()
            getViewModel().addCustomerOrder(loginCustomer.username)
            getViewModel().deleteCustomerShopCart(loginCustomer.username)
            getNavController().navigate(OrderFragmentDirections.actionOrderFragmentToWatchesItem())
        }

    }

    private fun initRecipesRecyclerView(){
        getViewDataBinding().ordersRv.layoutManager = LinearLayoutManager(context)
        getViewDataBinding().ordersRv.setHasFixedSize(true)
        // set Animation to all children (items) of this Layout
        getViewDataBinding().ordersRv.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        getViewDataBinding().ordersRv.adapter = ordersAdapter
    }

    override val layoutId: Int
        get() = R.layout.fragment_order
    override val bindingVariable: Int
        get() = BR.orderViewModel

    override fun getViewModel(): OrderViewModel {
       return orderViewModel
    }
}