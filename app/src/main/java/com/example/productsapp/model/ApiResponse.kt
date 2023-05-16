package com.example.productsapp.model

data class ApiResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)