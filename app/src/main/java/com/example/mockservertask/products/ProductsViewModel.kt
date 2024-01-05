package com.example.mockservertask.products

import android.util.Log
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


    private  val TAG = "ProductsViewModel"
    fun getProducts() {
        executeSharedApi(_productFlow) {
            usecase.getProducts().onStart {
                Log.e(TAG, "getProducts: start", )
                _productFlow.emit(NetWorkState.Loading) }
                .onCompletion { _productFlow.emit(NetWorkState.DismissLoading)
                    Log.e(TAG, "getProducts: compl", )}
                .catch { _productFlow.emit(NetWorkState.Error(it))
                    Log.e(TAG, "getProducts: error", )}
                .collectLatest { _productFlow.emit(NetWorkState.Success(it))
                    Log.e(TAG, "getProducts: collct $it", )}
        }
    }
}