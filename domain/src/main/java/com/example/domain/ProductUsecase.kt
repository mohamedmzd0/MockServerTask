package com.example.domain

import com.example.data.entity.ProductItem
import com.example.data.repositories.ProductRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ProductUsecase @Inject constructor(private val repo: ProductRepo) {

    suspend fun getProducts(): Flow<List<ProductItem>> {

        return repo.getProductsFromRemote().transformResponseData { emit(it) }.onEach {
            repo.saveData(it)
        }
    }


    suspend fun getCachedProducts(): List<ProductItem> {
        return repo.getProductsFromDB()
    }

}

