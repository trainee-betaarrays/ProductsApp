package com.example.productsapp.service

import com.example.productsapp.model.ApiResponse
import retrofit2.Response

interface ProductService {
    suspend fun getProducts(): Response<ApiResponse>
}