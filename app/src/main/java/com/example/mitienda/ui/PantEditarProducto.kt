package com.example.mitienda.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mitienda.viewmodel.TiendaViewModel

@Composable
fun PantEditarProducto(
    id: String,
    navegaAtras: () -> Unit,
    tiendaViewModel: TiendaViewModel
) {
    val p = tiendaViewModel.getProducto(id)

    if (p == null) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Producto no encontrado")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = navegaAtras, modifier = Modifier.fillMaxWidth()) { Text("Atrás") }
        }
        return
    }

    val nombre = remember { mutableStateOf(p.nombre) }
    val precio = remember { mutableStateOf(p.precio) }
    val descripcion = remember { mutableStateOf(p.descripcion) }
    val imageUrl = remember { mutableStateOf(p.imageUrl) }

    val error = remember { mutableStateOf<String?>(null) }
    val guardando = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Editar producto")

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = nombre.value,
            onValueChange = { nombre.value = it },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = precio.value,
            onValueChange = { precio.value = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = descripcion.value,
            onValueChange = { descripcion.value = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = imageUrl.value,
            onValueChange = { imageUrl.value = it },
            label = { Text("URL imagen") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (error.value != null) {
            Text(error.value ?: "")
            Spacer(modifier = Modifier.height(10.dp))
        }

        Button(
            onClick = {
                error.value = null

                val ok = nombre.value.isNotBlank() &&
                        precio.value.isNotBlank() &&
                        descripcion.value.isNotBlank() &&
                        imageUrl.value.isNotBlank()

                if (!ok) {
                    error.value = "Rellena todos los campos"
                    return@Button
                }

                if (guardando.value) return@Button
                guardando.value = true

                tiendaViewModel.actualizarProducto(
                    id = id,
                    nombre = nombre.value,
                    precio = precio.value,
                    descripcion = descripcion.value,
                    imageUrl = imageUrl.value,
                    onOk = {
                        guardando.value = false
                        navegaAtras()
                    },
                    onError = {
                        guardando.value = false
                        error.value = it
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (guardando.value) "Actualizando..." else "Actualizar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = navegaAtras, modifier = Modifier.fillMaxWidth()) {
            Text("Atrás")
        }
    }
}