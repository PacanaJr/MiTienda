package com.example.mitienda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mitienda.navigation.GestionNavegacion
import com.example.mitienda.ui.theme.MiTiendaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiTiendaTheme {
                GestionNavegacion()
            }
        }
    }
}