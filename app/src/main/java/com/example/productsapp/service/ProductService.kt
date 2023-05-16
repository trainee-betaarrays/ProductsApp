package com.example.productsapp.service

import com.example.productsapp.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProducts(): Response<ApiResponse>
}