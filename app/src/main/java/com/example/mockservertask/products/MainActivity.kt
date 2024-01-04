package com.example.mockservertask.products

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.base.BaseActivity
import com.example.data.entity.ProductItem
import com.example.mockservertask.databinding.ActivityMainBinding
import com.example.mockservertask.details.ProductDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var rootBinding: ActivityMainBinding
    private val productsAdapter by lazy { ProductsAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootBinding = ActivityMainBinding.inflate(layoutInflater)



        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        rootBinding.rvProducts.apply {
            setHasFixedSize(true)
            adapter = productsAdapter
        }

        productsAdapter.onItemClick = ::onItemClicked
    }

    private fun onItemClicked(productItem: ProductItem) {
        val intent=Intent(this,ProductDetailsActivity::class.java)
        intent.putExtra(ProductDetailsActivity.Product,productItem)
        startActivity(intent)
    }
}