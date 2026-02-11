package com.example.mitienda.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.mitienda.viewmodel.TiendaViewModel

@Composable
fun PantDetalles(
    id: String,
    navegaAtras: () -> Unit,
    tiendaViewModel: TiendaViewModel
) {
    val p = tiendaViewModel.getProducto(id)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        if (p == null) {
            Text("Producto no encontrado")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = navegaAtras, modifier = Modifier.fillMaxWidth()) { Text("Atrás") }
            return
        }

        AsyncImage(
            model = p.imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(260.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = p.nombre,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${p.precio} €",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(p.descripcion)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = navegaAtras, modifier = Modifier.fillMaxWidth()) {
            Text("Atrás")
        }
    }
}