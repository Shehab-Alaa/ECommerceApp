package com.example.e_commerceapp.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.service.FirebaseDataSource
import com.example.e_commerceapp.data.service.FirebaseRepository
import com.example.e_commerceapp.ui.main.HomeViewModel
import com.example.e_commerceapp.ui.main.add_product.AddProductViewModel
import com.example.e_commerceapp.ui.main.customer_profile.CustomerProfileViewModel
import com.example.e_commerceapp.ui.main.login.LoginViewModel
import com.example.e_commerceapp.ui.main.product.ProductsViewModel
import com.example.e_commerceapp.ui.main.product_details.ProductDetailsViewModel
import com.example.e_commerceapp.ui.main.shop_cart.ShopCartViewModel
import com.example.e_commerceapp.ui.main.sign_up.SignUpViewModel
import com.example.e_commerceapp.utils.AppConstants
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { provideFirebaseReference()}
    single { provideFirebaseRepository(get())}

    viewModel { (handle : SavedStateHandle) -> HomeViewModel(get(), handle) }
    viewModel { (handle : SavedStateHandle) -> AddProductViewModel(get(), handle) }
    viewModel { (handle : SavedStateHandle) -> CustomerProfileViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> LoginViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> ProductsViewModel(get()  , handle) }
    viewModel { (handle : SavedStateHandle) -> ProductDetailsViewModel(get()  , handle ) }
    viewModel { (handle : SavedStateHandle) -> ShopCartViewModel(get()  , handle) }
    viewModel { (handle : SavedStateHandle) -> SignUpViewModel(get() , handle) }

    single { provideSharedPreferences(get())}
}


private fun provideAppContext(application: Application) = application

private fun provideFirebaseReference() : DatabaseReference = Firebase.database.reference
private fun provideFirebaseRepository(databaseReference : DatabaseReference) : FirebaseDataSource  = FirebaseRepository(databaseReference)

private fun provideSharedPreferences(context: Context) = context.getSharedPreferences(AppConstants.PREF_NAME , Context.MODE_PRIVATE)
