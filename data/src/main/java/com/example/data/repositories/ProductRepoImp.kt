package com.example.data.repositories

import android.util.Log
import com.example.data.entity.ProductItem
import com.example.data.remote.ProductsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject


private const val TAG = "ProductRepoImp"

class ProductRepoImp @Inject constructor(private val productsApi: ProductsApi) : ProductRepo {
    override suspend fun getProductsFromRemote(): Flow<Response<List<ProductItem>>> {
        Log.e(TAG, "getProductsFromRemote: ", )
        return flow { emit(productsApi.getProducts()) }
    }

    override suspend fun getProductsFromDB(): Flow<List<ProductItem>> {
        TODO("Not yet implemented")
    }


}