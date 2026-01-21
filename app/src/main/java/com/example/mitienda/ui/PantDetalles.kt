package com.example.mitienda.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantDetalles(
    id: String,
    navegaAConfirmacion: (String) -> Unit,
    navegaAtras: () -> Unit
) {
    val moto = MotosData.getById(id)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        },
        backgroundColor = Color(0xFFF6F6FA)
    ) { paddingValues ->
        if (moto == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Producto no encontrado")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = navegaAtras) { Text("Atrás") }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .background(Color(0xFFE9E9EE), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = moto.imagenRes),
                        contentDescription = moto.nombre,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = moto.nombre,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${"%.2f".format(moto.precio)} €",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF3E537A)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Descripción del producto: ${moto.descripcion}",
                    fontSize = 13.sp,
                    color = Color(0xFF6B6B74),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { navegaAConfirmacion(moto.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(22.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF3E537A),
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
                ) {
                    Text("Comprar ahora", fontSize = 13.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = navegaAtras,
                    modifier = Modifier.height(36.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF3E537A),
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
                ) {
                    Text("Atrás", fontSize = 12.sp)
                }
            }
        }
    }
}
