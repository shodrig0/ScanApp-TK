package com.rodrigov.myapplication.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    val id: Int,

    @SerialName("codigo_barra")
    val codigoBarra: String,

    val descripcion: String,

    @SerialName("precio_unidad")
    val precioUnidad: Double,

    @SerialName("fecha_actualizacion")
    val fechaActualizacion: String
)