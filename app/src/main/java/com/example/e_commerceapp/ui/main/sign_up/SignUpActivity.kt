package com.example.e_commerceapp.ui.main.sign_up

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.observe
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.databinding.ActivitySignUpBinding
import com.example.e_commerceapp.ui.base.BaseActivity
import com.example.e_commerceapp.ui.main.HomeActivity
import com.example.e_commerceapp.ui.main.login.LoginActivity
import com.example.e_commerceapp.utils.AppConstants
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SignUpActivity:BaseActivity<ActivitySignUpBinding , SignUpViewModel>(),
    DatePickerDialog.OnDateSetListener {

    private val signUpViewModel : SignUpViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }
    private lateinit var customer: Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewDataBinding().birthdayEditText.setOnClickListener(View.OnClickListener {
             DatePickerDialog(this , this , 1998 , 4 , 28).show()
        })

        getViewDataBinding().signUpButton.setOnClickListener {
            // check for all texts that they contains input values and toast for any error
            when {
                getViewDataBinding().usernameEditText.text.toString() == "" -> {
                    toastMessage("please choose username")
                }
                getViewDataBinding().passwordEditText.text.toString() == "" -> {
                    toastMessage("please choose password")
                }
                getViewDataBinding().birthdayEditText.text.toString() == "" -> {
                    toastMessage("please choose birthday")
                }
                getViewDataBinding().jobEditText.text.toString() == "" -> {
                    toastMessage("please enter your job")
                }
                else -> {
                    customer = Customer(
                        getViewDataBinding().usernameEditText.text.toString(),
                        getViewDataBinding().passwordEditText.text.toString(),
                        getViewDataBinding().genderSpinner.selectedItem.toString(),
                        getViewDataBinding().birthdayEditText.text.toString(),
                        getViewDataBinding().jobEditText.text.toString()
                    )
                    getViewModel().signUpNewCustomer(customer)
                }
            }
        }

        getViewModel().validSignUpLiveData.observe(this) {
            if (it){
                // navigate to the app
                navigateToApp(customer)
            }else{
                // this username is taken / choose another one
                Toast.makeText(this , "this username is already taken" , Toast.LENGTH_SHORT).show()
                getViewDataBinding().usernameEditText.requestFocus()
            }
        }

        getViewDataBinding().toSignInBtn.setOnClickListener{
            finish()
            startActivity(Intent(this , LoginActivity::class.java))
        }
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val date = "$month/$day/$year"
        getViewDataBinding().birthdayEditText.setText(date)
    }

    private fun navigateToApp(customer : Customer){
        finish()
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(AppConstants.LOGIN_CUSTOMER, customer)
        startActivity(intent)
    }

    private fun toastMessage(message:String){
        Toast.makeText(this , message , Toast.LENGTH_LONG).show()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_sign_up
    }

    override val bindingVariable: Int
        get() = BR.signUpViewModel

    override fun getViewModel(): SignUpViewModel {
        return signUpViewModel
    }

}