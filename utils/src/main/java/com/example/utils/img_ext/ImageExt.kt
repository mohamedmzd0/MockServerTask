package com.example.utils.img_ext

import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder


fun ImageView.generateQrCode(content: String) {
    try {
        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 512, 512)
        setImageBitmap(bitmap)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}