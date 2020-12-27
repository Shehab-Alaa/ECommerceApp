package com.example.e_commerceapp.ui.main.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityLoginBinding
import com.example.e_commerceapp.ui.base.BaseActivity
import com.example.e_commerceapp.ui.main.HomeActivity
import com.example.e_commerceapp.utils.AppConstants
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginActivity:BaseActivity<ActivityLoginBinding , LoginViewModel>() {

    private val loginViewModel : LoginViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewDataBinding().loginButton.setOnClickListener {
            when {
                getViewDataBinding().usernameEditText.text.isEmpty() -> {
                    Toast.makeText(this , "Please enter you username." , Toast.LENGTH_SHORT)
                }
                getViewDataBinding().passwordEditText.text.isEmpty() -> {
                    Toast.makeText(this , "Please enter you password." , Toast.LENGTH_SHORT)
                }
                else -> {
                    getViewModel().fetchCustomerData(getViewDataBinding().usernameEditText.text.toString() , getViewDataBinding().passwordEditText.text.toString())
                }
            }
        }

        getViewModel().customerLiveData.observe(this , {
            val intent = Intent(this , HomeActivity::class.java)
            intent.putExtra(AppConstants.LOGIN_CUSTOMER , it)
            startActivity(intent)
        })

        getViewModel().validLoginLiveData.observe(this , {
            if (it==false){
                Toast.makeText(this , "Invalid Login" , Toast.LENGTH_SHORT)
            }
        })

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override val bindingVariable: Int
        get() = BR.loginViewModel

    override fun getViewModel(): LoginViewModel {
        return loginViewModel
    }

}