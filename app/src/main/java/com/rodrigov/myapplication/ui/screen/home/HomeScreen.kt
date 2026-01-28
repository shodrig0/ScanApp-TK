package com.rodrigov.myapplication.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    handleConfigIpClick: () -> Unit,
    handleSearchProductClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd).padding(top = 60.dp),
            onClick = handleConfigIpClick
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Configurar IP",
                tint = Color.DarkGray,
                modifier = Modifier.size(48.dp)
            )
        }

        Button(
            modifier = Modifier.align(Alignment.Center).fillMaxWidth(0.8f),
            shape = RoundedCornerShape(8.dp),
            onClick = handleSearchProductClick,
        ) {
            Text("Buscar Producto")
        }
    }
}