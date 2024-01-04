package com.example.mockservertask.products

import com.example.base.BaseViewModel
import com.example.data.NetWorkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


class ProductsViewModel : BaseViewModel() {


    private val _productFlow = MutableSharedFlow<NetWorkState>()
    val productFlow get() = _productFlow.asSharedFlow()


    fun getProducts() {

    }
}