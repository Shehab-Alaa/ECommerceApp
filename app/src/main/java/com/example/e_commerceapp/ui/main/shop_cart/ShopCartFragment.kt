package com.example.e_commerceapp.ui.main.shop_cart

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.model.Order
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.databinding.FragmentShopCartBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import com.example.e_commerceapp.ui.main.HomeActivity
import com.example.e_commerceapp.utils.AppConstants
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*

class ShopCartFragment:BaseFragment<FragmentShopCartBinding , ShopCartViewModel>(),
    ShopCartProductsAdapter.CartProductsAdapterListener {

    private lateinit var shopCartViewModel : ShopCartViewModel
    private lateinit var loginCustomer : Customer
    private val cartProductsAdapter = ShopCartProductsAdapter(mutableListOf() , this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginCustomer = (activity as HomeActivity).getLoginCustomer()
        shopCartViewModel = getViewModel { parametersOf(SavedStateHandle(mapOf(AppConstants.LOGIN_CUSTOMER to loginCustomer.username))) }

        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initRecipesRecyclerView()

        getViewDataBinding().orderNowBtn.setOnClickListener {
            // set orderDate , deliveryLocation , total price ==> before OrderCheckout.

            val currentDateTime = Calendar.getInstance().time


            val totalPrice = calcTotalPrice(cartProductsAdapter.getItems())
            val order = Order(loginCustomer.username , cartProductsAdapter.getItems() , "" , currentDateTime.toString(), totalPrice)
            getNavController().navigate(ShopCartFragmentDirections.actionShopCartItemToOrderFragment(order))
        }
    }

    private fun calcTotalPrice(productsList : MutableList<Product>) : Double{
        var totalPrice = 0.0
        productsList.onEach {
            totalPrice += (it.price * it.quantity)
        }
        return totalPrice
    }

    private fun initRecipesRecyclerView(){
        getViewDataBinding().cartProductsRv.layoutManager = LinearLayoutManager(context)
        getViewDataBinding().cartProductsRv.setHasFixedSize(true)
        // set Animation to all children (items) of this Layout
        getViewDataBinding().cartProductsRv.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        getViewDataBinding().cartProductsRv.adapter = cartProductsAdapter
    }

    override fun incProductQuantity(view: View, product: Product) {
        getViewModel().updateProductQuantity(product.name, product.quantity)
    }

    override fun decProductQuantity(view: View, product: Product) {
        getViewModel().updateProductQuantity(product.name , product.quantity)
    }

    override fun deleteProductFromCart(view: View, product: Product) {
       getViewModel().deleteShopCartProduct(product.name)
       Toast.makeText(context , "product is removed from cart" , Toast.LENGTH_SHORT).show()
    }

    override val layoutId: Int
        get() = R.layout.fragment_shop_cart
    override val bindingVariable: Int
        get() = BR.shopCartViewModel

    override fun getViewModel(): ShopCartViewModel {
        return shopCartViewModel
    }

}