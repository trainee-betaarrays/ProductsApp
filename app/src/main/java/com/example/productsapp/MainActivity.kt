package com.example.productsapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productsapp.api.RetrofitHelper
import com.example.productsapp.databinding.ActivityMainBinding
import com.example.productsapp.repository.ProductRepo
import com.example.productsapp.repository.Response
import com.example.productsapp.service.ProductService
import com.example.productsapp.ui.adapter.ProductsAdapter
import com.example.productsapp.ui.adapter.RecyclerViewClickListener
import com.example.productsapp.viewmodel.ProductsViewModel
import com.example.productsapp.viewmodel.ProductsViewModelFactory

class MainActivity : AppCompatActivity(), RecyclerViewClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productsRepo: ProductRepo
    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var productsRv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        setUi()
    }

    private fun setUi() {
        productsViewModel.products.observe(this) { fetchedData ->
            when (fetchedData){
                is Response.Loading -> { binding.progressBar.visibility = View.VISIBLE }
                is Response.Error -> {
                    binding.noInternetTV.text = fetchedData.errorMessage
                    binding.noInternetTV.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
                is Response.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.noInternetTV.visibility = View.GONE
                    fetchedData.data?.let {
                        val adapter = ProductsAdapter(this, fetchedData.data.products)
                        adapter.setOnItemClickListener(this)
                        productsRv.adapter = adapter
                    }
                }
            }
        }
    }

    private fun initialize() {
        val productService = RetrofitHelper.getRetrofitInstance().create(ProductService::class.java)
        productsRepo = ProductRepo(productService, applicationContext)
        productsViewModel = ViewModelProvider(this, ProductsViewModelFactory(productsRepo))[ProductsViewModel::class.java]
        productsViewModel.fetchProducts();
        productsRv = binding.productRv
        binding.productRv.layoutManager = LinearLayoutManager(this)
    }

    override fun recyclerViewListClicked(id: Int) {
        Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()
    }
}