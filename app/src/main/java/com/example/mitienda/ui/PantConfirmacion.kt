package com.example.mitienda.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantConfirmacion(
    id: String,
    vuelveHome: () -> Unit,
    navegaAtras: () -> Unit
) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6FA)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("✓", fontSize = 70.sp, color = Color(0xFF1E4BFF), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            Text("¡Compra realizada!", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(14.dp))
            Button(
                onClick = vuelveHome,
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF3E537A),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
            ) {
                Text("Volver a la Tienda", fontSize = 12.sp)
            }
        }
    }
}
