package com.rodrigov.myapplication.domain.repository

import android.util.Log
import com.rodrigov.myapplication.data.model.ProductDTO
import com.rodrigov.myapplication.data.network.HttpClientProvider
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.isSuccess

suspend fun getCodigoProducto(
    ip: String,
    codigo: String
): ProductDTO {

    val codigoLimpio = codigo.trim().filter { it.isDigit() }.let {
        when (it.length) {
            12 -> "0$it"
            13 -> it
            else -> throw IllegalArgumentException("Codigo inválido: $it")
        }
    }

    Log.d(
        "GetProduct",
        "Codigo original='$codigo' limpio='$codigoLimpio'"
    )

    val url = "http://$ip/producto/$codigoLimpio"

    return try {
        val response = HttpClientProvider.client.get(url) {
            accept(ContentType.Application.Json)
        }

        if (!response.status.isSuccess()) {
            throw Exception("HTTP ${response.status}")
        }

        response.body()

    } catch (e: Exception) {
        Log.e("GetProduct", "Error fetch producto", e)
        throw e
    }
}

