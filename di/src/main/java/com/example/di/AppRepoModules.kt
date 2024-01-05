package com.example.di

import com.example.data.repositories.ProductRepo
import com.example.data.repositories.ProductRepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppRepoModules {

    @Binds
    abstract fun providesProductRepo(repo: ProductRepoImp): ProductRepo
}