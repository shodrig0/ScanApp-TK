package com.rodrigov.myapplication.ui.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun InfoRow(label: String, value: String, modifier: Modifier = Modifier, isHighlight: Boolean = false) {
    Column(modifier = modifier) {
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
        Text(
            text = value,
            style = if (isHighlight) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium,
            fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Normal
        )
    }
}