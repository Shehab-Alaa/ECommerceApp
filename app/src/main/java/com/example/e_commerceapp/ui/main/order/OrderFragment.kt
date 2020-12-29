package com.example.e_commerceapp.ui.main.order

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentOrderBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import com.example.e_commerceapp.ui.main.product.ProductsFragmentArgs
import com.example.e_commerceapp.utils.AppConstants
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class OrderFragment:BaseFragment<FragmentOrderBinding , OrderViewModel>() {

    private lateinit var orderViewModel : OrderViewModel
    private val ordersAdapter = OrdersAdapter(mutableListOf())

    // get user location on map and save it
    // change classes to match firebase database
    // push orders to username child in orders child

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = OrderFragmentArgs.fromBundle(requireArguments())
        orderViewModel = getViewModel{ parametersOf(SavedStateHandle(mapOf(AppConstants.SELECTED_ORDER to args.selectedOrder))) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(false)
        initRecipesRecyclerView()

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