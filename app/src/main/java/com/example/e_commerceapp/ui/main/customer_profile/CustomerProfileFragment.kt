package com.example.e_commerceapp.ui.main.customer_profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.databinding.FragmentCustomerProfileBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import com.example.e_commerceapp.ui.main.HomeActivity
import com.example.e_commerceapp.ui.main.login.LoginActivity
import com.example.e_commerceapp.ui.main.product.ProductsFragmentDirections
import com.example.e_commerceapp.utils.AppConstants
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CustomerProfileFragment:BaseFragment<FragmentCustomerProfileBinding , CustomerProfileViewModel>() {

    private lateinit var customerProfileViewModel : CustomerProfileViewModel
    private lateinit var loginCustomer : Customer
    private val sharedPreferences : SharedPreferences by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginCustomer = (activity as HomeActivity).getLoginCustomer()
        customerProfileViewModel = getViewModel { parametersOf(SavedStateHandle(mapOf(AppConstants.LOGIN_CUSTOMER to loginCustomer))) }

        super.onViewCreated(view, savedInstanceState)

        setLayoutAnimations()

        getViewDataBinding().logoutBtn.setOnClickListener {
            // delete customer (remember me) from shared preference if exists
            // navigate to LoginActivity

            sharedPreferences.edit().clear().apply()

            (activity as HomeActivity).finish()
            val intent = Intent(activity , LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun  setLayoutAnimations(){
        val rightAnimationController: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right)
        getViewDataBinding().profileLayout.layoutAnimation = rightAnimationController

        val bottomAnimationController: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom)
        getViewDataBinding().infoLayout.layoutAnimation = bottomAnimationController
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_shop_cart)
            getNavController().navigate(CustomerProfileFragmentDirections.actionCustomerProfileItemToShopCartItem())
        return super.onOptionsItemSelected(item)
    }

    override val layoutId: Int
        get() = R.layout.fragment_customer_profile
    override val bindingVariable: Int
        get() = BR.customerProfileViewModel

    override fun getViewModel(): CustomerProfileViewModel {
        return customerProfileViewModel
    }
}