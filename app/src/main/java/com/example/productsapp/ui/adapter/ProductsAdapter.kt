package com.example.productsapp.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
        Glide.with(context).load(product.thumbnail).listener(object :RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                holder.progressBar.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                holder.progressBar.visibility = View.GONE
                return false
            }

        }).diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .fitCenter()
            .into(holder.productIV)

        holder.productTitleTV.text = product.title
        holder.productDescriptionTV.text = product.description

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
        val progressBar: ProgressBar = itemView.findViewById(R.id.productProgressBar)
    }
}