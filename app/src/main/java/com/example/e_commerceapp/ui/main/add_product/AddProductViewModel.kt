package com.example.e_commerceapp.ui.main.add_product

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.data.service.FirebaseDataSource
import com.example.e_commerceapp.ui.base.BaseViewModel

class AddProductViewModel(firebaseRepository: FirebaseDataSource, saveStateHandle: SavedStateHandle) : BaseViewModel(firebaseRepository,saveStateHandle) {

    private val productImageData = MutableLiveData<String>()
    val productImageLiveData : LiveData<String> get() = productImageData

    fun uploadProductImageToFirebase(imageUri: Uri){
        getFirebaseRepository().getUploadImageFirebaseTask(imageUri)
             .addOnSuccessListener { taskSnapshot ->
                 val result = taskSnapshot.metadata?.reference?.downloadUrl
                 result?.addOnSuccessListener {
                     productImageData.value = it.toString()
                 }
             }.addOnFailureListener {
                 productImageData.value = "Default"
            }
    }

    fun addProductToFirebase(product: Product , categoryName:String){
        getFirebaseRepository().pushProductToFirebase(product,categoryName)
    }

}