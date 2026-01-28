package com.rodrigov.myapplication.ui.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rodrigov.myapplication.ui.screen.components.BackButton

@Composable
fun SearchProductScreen(
    onSearch: (String) -> Unit,
    onBack: () -> Unit
) {
    var codigo by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(codigo) {
        if (codigo.isEmpty()) {
            return@LaunchedEffect
        }

        kotlinx.coroutines.delay(1000)

        if ((codigo.length == 12 || codigo.length == 13)) {
            onSearch(codigo)
            codigo = ""
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(24.dp),
    ) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Buscar producto",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(32.dp))

            OutlinedTextField(
                value = codigo,
                onValueChange = { codigo = it.filter(Char::isDigit) },
                label = { Text("Código") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .focusRequester(focusRequester)
            )

            Spacer(Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(8.dp),
                enabled = codigo.length == 12 || codigo.length == 13,
                onClick = { onSearch(codigo) }
            ) {
                Text("Buscar producto")
            }
        }

        BackButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onBack
        )
    }
}
