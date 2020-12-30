package com.example.e_commerceapp.ui.main.customer_profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentCustomerProfileBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import com.example.e_commerceapp.ui.main.HomeActivity
import com.example.e_commerceapp.ui.main.login.LoginActivity
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CustomerProfileFragment:BaseFragment<FragmentCustomerProfileBinding , CustomerProfileViewModel>() {

    private val customerProfileViewModel : CustomerProfileViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }
    private val sharedPreferences : SharedPreferences by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        getViewDataBinding().logoutBtn.setOnClickListener {
            // delete customer (remember me) from shared preference if exists
            // navigate to LoginActivity

            sharedPreferences.edit().clear().apply()

            (activity as HomeActivity).finish()
            val intent = Intent(activity , LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_customer_profile
    override val bindingVariable: Int
        get() = BR.customerProfileViewModel

    override fun getViewModel(): CustomerProfileViewModel {
        return customerProfileViewModel
    }
}