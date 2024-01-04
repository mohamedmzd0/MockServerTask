package com.example.mockservertask.details

import android.os.Build
import android.os.Bundle
import com.example.base.BaseActivity
import com.example.data.entity.ProductItem
import com.example.mockservertask.databinding.ActivityProductDetailsBinding


class ProductDetailsActivity : BaseActivity() {
    private lateinit var rootBinding: ActivityProductDetailsBinding

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootBinding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(rootBinding.root)


        val item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableExtra(Product, ProductItem::class.java)
        else
            intent.getParcelableExtra(Product)

        setupViews(item)

    }

    private fun setupViews(item: ProductItem?) {
        rootBinding.apply {

        }
    }

    companion object {
        const val Product = "product"
    }
}