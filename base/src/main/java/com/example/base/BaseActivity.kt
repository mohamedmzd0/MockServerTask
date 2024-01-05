package com.example.base

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.data.ErrorAPI
import com.example.data.NetWorkState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

private const val TAG = "BaseActivity"
open class BaseActivity : AppCompatActivity() {


    fun handleSharedFlow(
        productFlow: SharedFlow<NetWorkState>,
        onSuccess: (data: Any) -> Unit
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                productFlow.collect { networkState ->
                    Log.e(TAG, "handleSharedFlow: ", )
                    when (networkState) {
                        is NetWorkState.Success<*> -> {
                            onSuccess(networkState.data!!)
                            Log.e(TAG, "handleSharedFlow: succes", )
                        }

                        is NetWorkState.Error -> {
                            handleErrorGeneral(networkState.th)
                            Log.e(TAG, "handleSharedFlow: error", )
                        }

                        else -> {}
                    }

                }
            }
        }
    }


    fun handleErrorGeneral(th: Throwable) {
        th.printStackTrace()
        when (th.message) {
            ErrorAPI.EMPTY_RESPONSE -> {
                Toast.makeText(this, "empty response", Toast.LENGTH_SHORT).show()
            }

            ErrorAPI.SERVER_ERROR -> {
                Toast.makeText(this, "server error", Toast.LENGTH_SHORT).show()
            }

            ErrorAPI.BAD_REQUEST -> {
                Toast.makeText(this, "bad request", Toast.LENGTH_SHORT).show()
            }

            ErrorAPI.UN_AUTHRIZED -> {
                // no need
                Toast.makeText(this, "unauth", Toast.LENGTH_SHORT).show()
            }
        }

    }
}