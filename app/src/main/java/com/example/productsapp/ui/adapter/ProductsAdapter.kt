package com.example.productsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productsapp.R
import com.example.productsapp.model.Product

class ProductsAdapter(private val context: Context, private val list: List<Product>):  RecyclerView.Adapter<ProductsAdapter.ViewHolder>(){
    private var onItemClickListener: RecyclerViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position]
        Glide.with(context).load(product.thumbnail).into(holder.productIV)
        holder.productTitleTV.text = product.title
        holder.productDescriptionTV.text = product.description
        holder.productBrandTV.text = product.brand
        holder.productPriceTV.text = product.price.toString()
        holder.productStockTV.text = product.stock.toString()
        holder.productRatingTV.text = product.rating.toString()

        holder.itemView.setOnClickListener{
            onItemClickListener?.recyclerViewListClicked(list[position].id)
        }
    }

    fun setOnItemClickListener(listener: RecyclerViewClickListener) {
        onItemClickListener = listener
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val productIV: ImageView = itemView.findViewById(R.id.idIVProductImg)
        val productTitleTV: TextView = itemView.findViewById(R.id.idIVProductTitle)
        val productDescriptionTV: TextView = itemView.findViewById(R.id.idTVProductDescription)
        val productBrandTV: TextView = itemView.findViewById(R.id.idTVProductBrand)
        val productPriceTV: TextView = itemView.findViewById(R.id.idTVProductPrice)
        val productStockTV: TextView = itemView.findViewById(R.id.idTVProductStock)
        val productRatingTV: TextView = itemView.findViewById(R.id.idTVProductRating)
    }
}