package com.rodrigov.myapplication.domain.repository

import android.util.Log
import com.rodrigov.myapplication.data.model.ProductDTO
import com.rodrigov.myapplication.data.network.HttpClientProvider
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

suspend fun updatePrecioProducto(
    ip: String,
    codigo: String,
    nuevoPrecio: Double
): ProductDTO {

    val codigoLimpio = codigo.trim().filter { it.isDigit() }.let {
        when (it.length) {
            12 -> "0$it"
            13 -> it
            else -> throw IllegalArgumentException("Codigo inválido: $it")
        }
    }

    val url = "http://$ip/producto/$codigoLimpio"

    return try {
        val response = HttpClientProvider.client.patch(url) {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)

            setBody(
                mapOf(
                    "precio_unidad" to nuevoPrecio
                )
            )
        }

        Log.d("UpdateProduct", "Status: ${response.status}")
        Log.d("UpdateProduct", "Raw body: ${response.bodyAsText()}")

        if (!response.status.isSuccess()) {
            throw Exception("HTTP ${response.status}")
        }

        response.body<ProductDTO>()

    } catch (e: Exception) {
        Log.e("UpdateProduct", "Error update producto", e)
        throw e
    }
}