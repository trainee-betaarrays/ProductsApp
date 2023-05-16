package com.example.productsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productsapp.model.ApiResponse
import com.example.productsapp.repository.ProductRepo
import com.example.productsapp.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel(private val repository: ProductRepo): ViewModel() {
    fun fetchProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProducts()
        }
    }

    val products: LiveData<Response<ApiResponse>>
        get() = repository.product
}