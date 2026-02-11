package com.example.mitienda.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mitienda.viewmodel.AuthViewModel
import com.example.mitienda.viewmodel.TiendaViewModel

@Composable
fun PantHome(
    navegaADetalle: (String) -> Unit,
    navegaAEditar: (String) -> Unit,
    onLogout: () -> Unit,
    tiendaViewModel: TiendaViewModel,
    authViewModel: AuthViewModel
) {
    val productos = tiendaViewModel.productos.value
    val errorVm = tiendaViewModel.error.value
    val cargando = tiendaViewModel.cargando.value

    val nombre = remember { mutableStateOf("") }
    val precio = remember { mutableStateOf("") }
    val descripcion = remember { mutableStateOf("") }
    val imageUrl = remember { mutableStateOf("") }

    val errorLocal = remember { mutableStateOf<String?>(null) }
    val guardando = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bienvenido ${authViewModel.usuarioEmail.value ?: ""}",
                modifier = Modifier.weight(1f)
            )

            Button(onClick = onLogout) {
                Text("Salir")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

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

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                errorLocal.value = null

                val ok = nombre.value.isNotBlank() &&
                        precio.value.isNotBlank() &&
                        descripcion.value.isNotBlank() &&
                        imageUrl.value.isNotBlank()

                if (!ok) {
                    errorLocal.value = "Rellena todos los campos"
                    return@Button
                }

                if (guardando.value) return@Button
                guardando.value = true

                tiendaViewModel.crearProducto(
                    nombre = nombre.value,
                    precio = precio.value,
                    descripcion = descripcion.value,
                    imageUrl = imageUrl.value,
                    onOk = {
                        guardando.value = false
                        nombre.value = ""
                        precio.value = ""
                        descripcion.value = ""
                        imageUrl.value = ""
                    },
                    onError = {
                        guardando.value = false
                        errorLocal.value = it
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (guardando.value) "Agregando..." else "Agregar Producto")
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (errorLocal.value != null) {
            Text(errorLocal.value ?: "")
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (errorVm != null) {
            Text(errorVm)
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (cargando) {
            Text("Cargando...")
            return
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(productos) { p ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 6.dp
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(p.nombre)
                            Text(p.precio)
                        }

                        Button(onClick = { navegaADetalle(p.id) }) {
                            Text("Ver")
                        }

                        Spacer(modifier = Modifier.height(0.dp))

                        Button(onClick = { navegaAEditar(p.id) }) {
                            Text("Editar")
                        }

                        Button(
                            onClick = {
                                tiendaViewModel.borrarProducto(
                                    id = p.id,
                                    onOk = { },
                                    onError = { errorLocal.value = it }
                                )
                            }
                        ) {
                            Text("Borrar")
                        }
                    }
                }
            }
        }
    }
}