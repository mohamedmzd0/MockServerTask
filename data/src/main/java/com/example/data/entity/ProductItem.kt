package com.example.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductItem(
    val id: Int,
    val name: String,
    val description: String,
    val qrCode: String,
    val barCode: String,
):Parcelable