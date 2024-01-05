package com.example.data.repositories

import com.example.data.db.AppDatabase
import com.example.data.entity.ProductItem
import com.example.data.remote.ProductsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject


class ProductRepoImp @Inject constructor(
    private val productsApi: ProductsApi,
    private val appDatabase: AppDatabase
) : ProductRepo {
    override suspend fun getProductsFromRemote(): Flow<Response<List<ProductItem>>> {
        return flow { emit(productsApi.getProducts()) }
    }
    override suspend fun getProductsFromDB() = appDatabase.appDao().getProducts()

    override suspend fun saveData(it: List<ProductItem>) {
        appDatabase.appDao().insertAndDeleteAll(it)
    }
}