package com.example.domain

import android.util.Log
import com.example.data.entity.ProductItem
import com.example.data.repositories.ProductRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "ProductUsecase"
class ProductUsecase @Inject constructor(private val repo: ProductRepo) {

    suspend fun getProducts(): Flow<List<ProductItem>> {
        Log.e(TAG, "getProducts: ", )
        return repo.getProductsFromRemote().transformResponseData { emit(it) }

    }


}

