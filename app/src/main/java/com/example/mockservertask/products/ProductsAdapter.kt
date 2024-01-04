package com.example.mockservertask.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entity.ProductItem
import com.example.mockservertask.R

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    private val arrayProducts = ArrayList<ProductItem>()

    var onItemClick: ((ProductItem) -> Unit)? = null

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view.rootView) {
        fun bind(productItem: ProductItem) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent, false))

    override fun getItemCount() = arrayProducts.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(arrayProducts[position])
}