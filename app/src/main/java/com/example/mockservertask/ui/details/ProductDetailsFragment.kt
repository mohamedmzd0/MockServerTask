package com.example.mockservertask.ui.details

import android.os.Build
import android.os.Bundle
import android.view.View
import com.example.base.BaseFragment
import com.example.data.entity.ProductItem
import com.example.mockservertask.R
import com.example.mockservertask.databinding.FragmentProductDetailsBinding
import com.example.utils.img_ext.generateQrCode


class ProductDetailsFragment : BaseFragment(R.layout.fragment_product_details) {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!


    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductDetailsBinding.bind(view)

        val item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("productItem", ProductItem::class.java)
        } else {
            arguments?.getParcelable("productItem")
        }
        setupViews(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViews(item: ProductItem?) {
        binding.apply {
            tvTitle.text = item?.name
            ivQRCode.generateQrCode(item?.qrCode ?: "")
        }
    }


}