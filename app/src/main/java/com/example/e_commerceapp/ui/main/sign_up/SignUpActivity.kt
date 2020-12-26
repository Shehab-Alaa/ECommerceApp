package com.example.e_commerceapp.ui.main.sign_up

import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivitySignUpBinding
import com.example.e_commerceapp.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SignUpActivity:BaseActivity<ActivitySignUpBinding , SignUpViewModel>() {

    private val signUpViewModel : SignUpViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }

    override fun getLayoutId(): Int {
        return R.layout.activity_sign_up
    }

    override val bindingVariable: Int
        get() = BR.signUpViewModel

    override fun getViewModel(): SignUpViewModel {
        return signUpViewModel
    }

}