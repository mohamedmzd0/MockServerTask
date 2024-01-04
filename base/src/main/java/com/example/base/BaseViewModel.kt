package com.example.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.NetWorkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val parentJob = Job()


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }


    fun executeSharedApi(
        state: MutableSharedFlow<NetWorkState>, job: Job = parentJob, action: suspend () -> Unit
    ) {
        viewModelScope.launch(handlerShared()) {
            action()
        }
    }

    private fun handlerShared() = CoroutineExceptionHandler { _, _ -> }


}