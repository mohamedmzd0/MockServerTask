package com.example.di

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody


class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.endsWith("products") -> productsResponse
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                responseString.toByteArray()
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()

    }

}


val productsResponse = "[\n" +
        "  {\n" +
        "    \"id\": 1,\n" +
        "    \"name\": \"Product 1\",\n" +
        "    \"description\": \"Description for Product 1\",\n" +
        "    \"qrCode\": \"QRCode1\",\n" +
        "    \"barCode\": \"BarCode1\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": 2,\n" +
        "    \"name\": \"Product 2\",\n" +
        "    \"description\": \"Description for Product 2\",\n" +
        "    \"qrCode\": \"QRCode2\",\n" +
        "    \"barCode\": \"BarCode2\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": 3,\n" +
        "    \"name\": \"Product 3\",\n" +
        "    \"description\": \"Description for Product 3\",\n" +
        "    \"qrCode\": \"QRCode3\",\n" +
        "    \"barCode\": \"BarCode3\"\n" +
        "  }\n" +
        "]\n"