package com.example.data.repositories

import androidx.lifecycle.LiveData
import com.example.data.entity.ProductItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRepo {

    suspend fun getProductsFromRemote(): Flow<Response<List<ProductItem>>>

    suspend fun getProductsFromDB(): List<ProductItem>
    suspend fun saveData(it: List<ProductItem>)


}