package com.example.e_commerceapp.ui.main.login

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityPasswordRecoveryBinding
import com.example.e_commerceapp.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PasswordRecoveryActivity:BaseActivity<ActivityPasswordRecoveryBinding, PasswordRecoveryViewModel>() {

    private val passwordRecoveryViewModel : PasswordRecoveryViewModel by viewModel { parametersOf(SavedStateHandle())}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        getViewDataBinding().recoverPasswordBtn.setOnClickListener {
            // check on empty text inputs
            // check if there is username that has same job to get his password recover from firebase
            getViewModel().getCustomerData(getViewDataBinding().usernameEditText.text.toString() , getViewDataBinding().jobEditText.text.toString())
        }

        getViewModel().customerLiveData.observe(this , {
            if (it != null){
                // customer exist and set his password recovery
                getViewDataBinding().passwordText.text = it.password
                Toast.makeText(this , "password recovered" , Toast.LENGTH_SHORT).show()
            }else{
                // fake customer
                Toast.makeText(this , "not valid customer data" , Toast.LENGTH_SHORT).show()
            }
        })
    }

    override val bindingVariable: Int
        get() = R.layout.activity_password_recovery

    override fun getLayoutId(): Int {
        return BR.passwordRecoveryViewModel
    }

    override fun getViewModel(): PasswordRecoveryViewModel {
        return passwordRecoveryViewModel
    }
}