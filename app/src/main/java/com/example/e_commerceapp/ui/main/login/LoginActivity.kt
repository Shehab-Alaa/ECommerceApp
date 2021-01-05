package com.example.e_commerceapp.ui.main.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.observe
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.databinding.ActivityLoginBinding
import com.example.e_commerceapp.ui.base.BaseActivity
import com.example.e_commerceapp.ui.main.HomeActivity
import com.example.e_commerceapp.ui.main.sign_up.SignUpActivity
import com.example.e_commerceapp.utils.AppConstants
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class LoginActivity:BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    private val loginViewModel : LoginViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }
    private val sharedPreferences : SharedPreferences by inject()


    override fun onStart() {
        super.onStart()

        // when open app check for any customer shared preference to navigate directly to the app.
        val json: String? = sharedPreferences.getString(AppConstants.REMEMBERED_CUSTOMER, "Nothing")
        if (json != "Nothing") {
            val customer: Customer = Gson().fromJson(json, Customer::class.java)
            navigateToApp(customer)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewDataBinding().loginButton.setOnClickListener {
            when {
                getViewDataBinding().usernameEditText.text.isEmpty() -> {
                    Toast.makeText(this, "please enter you username.", Toast.LENGTH_SHORT).show()
                }
                getViewDataBinding().passwordEditText.text.isEmpty() -> {
                    Toast.makeText(this, "please enter you password.", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    getViewModel().fetchCustomerData(
                        getViewDataBinding().usernameEditText.text.toString(),
                        getViewDataBinding().passwordEditText.text.toString()
                    )
                }
            }
        }

        getViewModel().customerLiveData.observe(this) {
            // if remember me is checked save customer to shared preference
            // when logout delete customer from shared preference
            // when open app check for any customer shared preference to navigate directly to the app after saving it.

            if (getViewDataBinding().rememberMeCheckbox.isChecked) {
                val prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
                val json = Gson().toJson(it)
                prefsEditor.putString(AppConstants.REMEMBERED_CUSTOMER, json)
                prefsEditor.apply()
            }

            // navigate to app
            navigateToApp(it)
        }

        getViewModel().validLoginLiveData.observe(this) {
            if (!it) {
                Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show()
            }
        }

        getViewDataBinding().forgetPasswordText.setOnClickListener {
            startActivity(Intent(this , PasswordRecoveryActivity::class.java))
        }

        getViewDataBinding().signUpText.setOnClickListener{
            val intent = Intent(this , SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun navigateToApp(customer : Customer){
        finish()
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(AppConstants.LOGIN_CUSTOMER, customer)
        startActivity(intent)
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