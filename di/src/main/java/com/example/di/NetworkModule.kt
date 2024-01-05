package com.example.di


import com.example.data.remote.ProductsApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

var VARIANT_URL = ""

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()

        okHttpClient.callTimeout(5, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(5, TimeUnit.SECONDS)
        okHttpClient.readTimeout(5, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(5, TimeUnit.SECONDS)
        okHttpClient.addNetworkInterceptor { chain ->

            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)

            requestBuilder.addHeader("Accept", "application/json")
            requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded")
            val request = requestBuilder
                .build()
            return@addNetworkInterceptor chain.proceed(request)
        }
        if (true) {
            okHttpClient.addInterceptor(loggingInterceptor)
        }
        okHttpClient.addInterceptor(MockInterceptor())
        okHttpClient.build()
        return okHttpClient.build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().serializeNulls().create())
    }

    @Provides
    fun providesBaseUrl(): String {
        return VARIANT_URL
    }

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideProductApi(retrofit: Retrofit): ProductsApi {
        return retrofit.create(ProductsApi::class.java)
    }
}