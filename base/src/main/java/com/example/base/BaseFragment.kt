package com.example.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.data.NetWorkState
import kotlinx.coroutines.flow.SharedFlow

open class BaseFragment(private val fragmentLayout: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(fragmentLayout, container, false)
    }

    protected fun handleSharedFlow(
        productFlow: SharedFlow<NetWorkState>,
        onSuccess: (data: Any) -> Unit
    ) = (activity as BaseActivity).handleSharedFlow(productFlow, onSuccess)

}