package com.example.mockservertask.products

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.BaseActivity
import com.example.data.NetWorkState
import com.example.data.entity.ProductItem
import com.example.mockservertask.databinding.ActivityMainBinding
import com.example.mockservertask.details.ProductDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var rootBinding: ActivityMainBinding
    private val productsAdapter by lazy { ProductsAdapter() }

    private val viewmodel by viewModels<ProductsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(rootBinding.root)



        setupRecyclerView()

        handleApiResponse()


        sendApiRequest()

    }


    override fun onStart() {
        super.onStart()
        sendApiRequest()
    }
    private fun sendApiRequest()=viewmodel.getProducts()
    private fun handleApiResponse() {
        handleSharedFlow(viewmodel.productFlow, onSuccess = {
            it as List<ProductItem>
            productsAdapter.addData(it)
        })

    }


    private fun setupRecyclerView() {
        rootBinding.rvProducts.apply {
            setHasFixedSize(true)
            adapter = productsAdapter
        }

        productsAdapter.onItemClick = ::onItemClicked
    }

    private fun onItemClicked(productItem: ProductItem) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra(ProductDetailsActivity.Product, productItem)
        startActivity(intent)
    }
}