package com.rodrigov.myapplication.ui.screen.result

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rodrigov.myapplication.data.model.ProductDTO
import com.rodrigov.myapplication.domain.repository.getCodigoProducto
import com.rodrigov.myapplication.domain.repository.updatePrecioProducto
import com.rodrigov.myapplication.ui.screen.components.BackButton
import com.rodrigov.myapplication.ui.screen.components.InfoRow
import com.rodrigov.myapplication.utils.AppDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun ResultScreen(
    codigo: String,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var producto by remember { mutableStateOf<ProductDTO?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    var precio by remember { mutableStateOf("") }

    LaunchedEffect(codigo) {

        isLoading = true
        error = null
        producto = null

        try {
            val ip = AppDataStore.getServerIp(context).first()
            val result = getCodigoProducto(ip, codigo)
            producto = result
            precio = result.precioUnidad.toString()
        } catch (e: Exception) {
            error = e.localizedMessage ?: "Error al buscar producto"
        } finally {
            isLoading = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Resultado", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(24.dp))

            when {
                isLoading -> {
                    CircularProgressIndicator()
                }

                error != null -> {
                    Text(error!!, color = MaterialTheme.colorScheme.error)
                }

                producto != null -> {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                "Detalles del Producto",
                                fontWeight = FontWeight.Bold
                            )

                            InfoRow("Producto", producto!!.descripcion)
                            InfoRow("Código", producto!!.codigoBarra)
                            InfoRow("Precio Unitario", producto!!.precioUnidad.toString())
                            InfoRow("Precio Unitario", producto!!.fechaActualizacion.take(10))

                            OutlinedTextField(
                                value = precio,
                                onValueChange = { precio = it.filter(Char::isDigit) },
                                label = { Text("Precio a imprimir") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            scope.launch {
                                try {
                                    val ip = AppDataStore.getServerIp(context).first()
                                    updatePrecioProducto(
                                        ip = ip,
                                        codigo = codigo,
                                        nuevoPrecio = precio.toDouble()
                                    )
                                    onBack()
                                } catch (e: Exception) {
                                    error = e.localizedMessage ?: "Error al actualizar"
                                }
                            }
                            Toast.makeText(
                                context,
                                "Precio actualizado correctamente",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    ) {
                        Text("Actualizar precio")
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        BackButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onBack
        )
    }
}
