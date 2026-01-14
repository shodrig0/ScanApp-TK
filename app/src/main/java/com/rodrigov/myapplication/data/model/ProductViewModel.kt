package com.rodrigov.myapplication.data.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ProductViewModel : ViewModel() {
    var producto by mutableStateOf<ProductDTO?>(null)
        private set

    fun setSelectedProduct(p: ProductDTO) {
        producto = p
    }
}