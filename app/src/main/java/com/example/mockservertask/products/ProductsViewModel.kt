package com.example.mockservertask.products

import com.example.base.BaseViewModel
import com.example.data.NetWorkState
import com.example.domain.ProductUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val usecase: ProductUsecase) : BaseViewModel() {


    private val _productFlow = MutableSharedFlow<NetWorkState>()
    val productFlow get() = _productFlow.asSharedFlow()


    fun getProducts() {
        executeSharedApi(_productFlow) {
            usecase.getProducts().onStart {
                _productFlow.emit(NetWorkState.Loading)
            }
                .onCompletion {
                    _productFlow.emit(NetWorkState.DismissLoading)
                }
                .catch {
                    _productFlow.emit(NetWorkState.Error(it))
                    _productFlow.emit(NetWorkState.Success(usecase.getCachedProducts()))
                }
                .collectLatest {
                    _productFlow.emit(NetWorkState.Success(it))
                }
        }
    }


}