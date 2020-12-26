package com.example.e_commerceapp.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.example.e_commerceapp.data.local.db.AppDatabase
import com.example.e_commerceapp.data.local.db.DatabaseRepository
import com.example.e_commerceapp.ui.main.HomeViewModel
import com.example.e_commerceapp.ui.main.add_product.AddProductViewModel
import com.example.e_commerceapp.ui.main.customer_profile.CustomerProfileViewModel
import com.example.e_commerceapp.ui.main.login.LoginViewModel
import com.example.e_commerceapp.ui.main.product.ProductsViewModel
import com.example.e_commerceapp.ui.main.product_details.ProductDetailsViewModel
import com.example.e_commerceapp.ui.main.shop_cart.ShopCartViewModel
import com.example.e_commerceapp.ui.main.sign_up.SignUpViewModel
import com.example.e_commerceapp.utils.AppConstants
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { provideAppDatabase(provideAppContext(get()) , provideDatabaseName())}
    single { provideDatabaseRepository(get()) }

    viewModel { (handle : SavedStateHandle) -> HomeViewModel(get() , handle ) }
    viewModel { (handle : SavedStateHandle) -> AddProductViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> CustomerProfileViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> LoginViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> ProductsViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> ProductDetailsViewModel(get() , handle ) }
    viewModel { (handle : SavedStateHandle) -> ShopCartViewModel(get() , handle) }
    viewModel { (handle : SavedStateHandle) -> SignUpViewModel(get() , handle) }

}

private fun provideDatabaseName() = AppConstants.DATABASE_NAME
private fun provideAppDatabase(context : Context, databaseName : String) = Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
    .allowMainThreadQueries().build()
private fun provideAppContext(application: Application) = application
private fun provideDatabaseRepository(appDatabase: AppDatabase) = DatabaseRepository(appDatabase)