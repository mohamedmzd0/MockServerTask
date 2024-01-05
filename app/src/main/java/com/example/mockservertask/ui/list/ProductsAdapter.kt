package com.example.mockservertask.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entity.ProductItem
import com.example.mockservertask.R
import com.example.mockservertask.databinding.ItemProductBinding

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    private val arrayProducts = ArrayList<ProductItem>()

    var onItemClick: ((ProductItem) -> Unit)? = null

    inner class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view.rootView) {
        init {

            view.setOnClickListener {
                if (adapterPosition== RecyclerView.NO_POSITION)return@setOnClickListener
                onItemClick?.invoke(arrayProducts[adapterPosition])
            }
        }
        fun bind(productItem: ProductItem) {
            ItemProductBinding.bind(view).apply {
                tvDetails.text=productItem.description
                tvTitle.text=productItem.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )

    override fun getItemCount() = arrayProducts.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(arrayProducts[position])

    fun addData(it: List<ProductItem>) {
        this.arrayProducts.clear()
        this.arrayProducts.addAll(it)
        notifyDataSetChanged()
    }
}