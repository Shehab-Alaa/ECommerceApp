package com.example.e_commerceapp.ui.main.login

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityLoginBinding
import com.example.e_commerceapp.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginActivity:BaseActivity<ActivityLoginBinding , LoginViewModel>() {

    private val loginViewModel : LoginViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override val bindingVariable: Int
        get() = BR.loginViewModel

    override fun getViewModel(): LoginViewModel {
        return loginViewModel
    }

}