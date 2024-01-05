package com.example.mockservertask.ui.list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.base.BaseFragment
import com.example.data.entity.ProductItem
import com.example.mockservertask.R
import com.example.mockservertask.databinding.FragmentProductsListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductsListFragment : BaseFragment(R.layout.fragment_products_list) {
    private var _binding: FragmentProductsListBinding? = null
    private val binding get() = _binding!!


    private val viewmodel by viewModels<ProductsViewModel>()

    private val productsAdapter by lazy { ProductsAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductsListBinding.bind(view)



        setupRecyclerView()

        handleApiResponse()


        sendApiRequest()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun sendApiRequest() = viewmodel.getProducts()
    private fun handleApiResponse() {
        handleSharedFlow(viewmodel.productFlow, onSuccess = {
            it as List<ProductItem>
            productsAdapter.addData(it)
        })

    }


    private fun setupRecyclerView() {
        binding.rvProducts.apply {
            setHasFixedSize(true)
            adapter = productsAdapter
        }

        productsAdapter.onItemClick = ::onItemClicked
    }

    private fun onItemClicked(productItem: ProductItem) {
        findNavController().navigate(
            R.id.action_productsListFragment_to_productDetailsFragment,
            bundleOf("productItem" to productItem)
        )
    }

}