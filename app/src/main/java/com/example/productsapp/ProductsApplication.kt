package com.example.productsapp

import android.app.Application
import com.example.productsapp.api.RetrofitHelper
import com.example.productsapp.repository.ProductRepo
import com.example.productsapp.service.ProductService

class ProductsApplication: Application() {

    lateinit var productsRepo: ProductRepo

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val weatherService = RetrofitHelper.getRetrofitInstance().create(ProductService::class.java)
        productsRepo = ProductRepo(weatherService, applicationContext)
    }
}