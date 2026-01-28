package com.rodrigov.myapplication.utils

fun formatDateIsoToDisplay(iso: String): String {
    return iso.take(10).split("-").reversed().joinToString("/")
}