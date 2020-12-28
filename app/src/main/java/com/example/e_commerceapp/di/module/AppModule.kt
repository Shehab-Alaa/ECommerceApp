package com.example.e_commerceapp.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.example.e_commerceapp.data.local.db.AppDatabase
import com.example.e_commerceapp.data.local.db.DatabaseDataSource
import com.example.e_commerceapp.data.local.db.DatabaseRepository
import com.example.e_commerceapp.data.remote.FirebaseDataSource
import com.example.e_commerceapp.data.remote.FirebaseRepository
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
    single { provideAppDatabase(provideAppContext(get()) , provideDatabaseName())}
    single { provideDatabaseRepository(get()) }

    viewModel { (handle : SavedStateHandle) -> HomeViewModel(get() , get() , handle) }
    viewModel { (handle : SavedStateHandle) -> AddProductViewModel(get() , get() , handle) }
    viewModel { (handle : SavedStateHandle) -> CustomerProfileViewModel(get() , get() , handle) }
    viewModel { (handle : SavedStateHandle) -> LoginViewModel(get() , get() , handle) }
    viewModel { (handle : SavedStateHandle) -> ProductsViewModel(get() , get() , handle) }
    viewModel { (handle : SavedStateHandle) -> ProductDetailsViewModel(get() , get() , handle ) }
    viewModel { (handle : SavedStateHandle) -> ShopCartViewModel(get() , get() , handle) }
    viewModel { (handle : SavedStateHandle) -> SignUpViewModel(get() , get() , handle) }

    single { provideSharedPreferences(get())}
}

private fun provideDatabaseName() = AppConstants.DATABASE_NAME
private fun provideAppDatabase(context : Context, databaseName : String) = Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
    .allowMainThreadQueries().build()
private fun provideAppContext(application: Application) = application
private fun provideDatabaseRepository(appDatabase: AppDatabase) : DatabaseDataSource = DatabaseRepository(appDatabase)

private fun provideFirebaseReference() : DatabaseReference = Firebase.database.reference
private fun provideFirebaseRepository(databaseReference : DatabaseReference) : FirebaseDataSource  = FirebaseRepository(databaseReference)

private fun provideSharedPreferences(context: Context) = context.getSharedPreferences(AppConstants.PREF_NAME , Context.MODE_PRIVATE)
