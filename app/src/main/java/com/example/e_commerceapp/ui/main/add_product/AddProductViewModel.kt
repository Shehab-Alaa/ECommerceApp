package com.example.e_commerceapp.ui.main.add_product

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.local.db.DatabaseDataSource
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.data.remote.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener

class AddProductViewModel(firebaseRepository: FirebaseDataSource, databaseRepository: DatabaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,databaseRepository,saveStateHandle) {

    private val productImageData = MutableLiveData<String>()
    val productImageLiveData : LiveData<String> get() = productImageData

    fun uploadProductImageToFirebase(imageUri: Uri){
        getFirebaseRepository().getUploadImageFirebaseTask(imageUri)
             .addOnSuccessListener { taskSnapshot ->
                 productImageData.value = taskSnapshot.metadata?.reference?.downloadUrl.toString()
             }.addOnFailureListener {
                 productImageData.value = "Default"
            }
    }

    fun addProductToFirebase(product: Product){
        getFirebaseRepository().pushProductToFirebase(product)
    }

}