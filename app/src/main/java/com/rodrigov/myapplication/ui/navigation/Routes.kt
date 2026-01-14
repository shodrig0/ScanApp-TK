package com.rodrigov.myapplication.ui.navigation

sealed class Routes(val route: String) {
    object Home: Routes("home")
    object ConfigIp: Routes("config_ip")
    object SearchProduct: Routes("search_product")
    object Result: Routes("result/{codigo}") {
        fun createRoute(codigo: String) = "result/$codigo"
    }
    object ScanBarcode: Routes("barcode")
}