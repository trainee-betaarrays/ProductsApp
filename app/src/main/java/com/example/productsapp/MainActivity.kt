package com.example.productsapp

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.productsapp.api.RetrofitHelper
import com.example.productsapp.databinding.ActivityMainBinding
import com.example.productsapp.repository.ProductRepo
import com.example.productsapp.service.ProductService
import com.example.productsapp.viewmodel.ProductsViewModel
import com.example.productsapp.viewmodel.ProductsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productsRepo: ProductRepo
    private lateinit var productsViewModel: ProductsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()

        productsViewModel.products.observe(this, Observer {
            Log.d("TAG", "onCreate: ${it.data}")
        })
    }

    private fun initialize() {
        val productService = RetrofitHelper.getRetrofitInstance().create(ProductService::class.java)
        productsRepo = ProductRepo(productService, applicationContext)
        productsViewModel = ViewModelProvider(this, ProductsViewModelFactory(productsRepo))[ProductsViewModel::class.java]
        productsViewModel.fetchProducts();
    }
}