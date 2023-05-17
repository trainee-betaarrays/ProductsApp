package com.example.productsapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.productsapp.model.ApiResponse
import com.example.productsapp.service.ProductService
import com.example.productsapp.util.NetworkUtils

class ProductRepo(
    private val productService: ProductService,
    private val context: Context
) {
    private val productsLiveData = MutableLiveData<Response<ApiResponse>>()
    val product: LiveData<Response<ApiResponse>>
        get() = productsLiveData

    suspend fun getProducts(){
        if (NetworkUtils.isInternetAvailable(context)){
            val result = productService.getProducts()
            if (result.isSuccessful && result.body() != null){
                productsLiveData.postValue(Response.Success(result.body()))
            } else {
                productsLiveData.postValue(Response.Error("Error 404: Data not found"))
            }
        } else {
            productsLiveData.postValue(Response.Error(errorMessage = "Please connect to a network"))
        }
    }
}