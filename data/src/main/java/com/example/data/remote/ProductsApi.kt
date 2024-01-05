package com.example.data.remote

import com.example.data.entity.ProductItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {


    @GET("products")
    suspend fun getProducts(): Response<List<ProductItem>>

}