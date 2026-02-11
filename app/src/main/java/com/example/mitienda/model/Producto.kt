package com.example.mitienda.model

import com.google.firebase.firestore.DocumentId

data class Producto(
    @DocumentId
    val id: String = "",
    val nombre: String = "",
    val precio: String = "",
    val descripcion: String = "",
    val imageUrl: String = ""
)