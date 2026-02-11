package com.example.mitienda.model

import com.google.firebase.firestore.DocumentId

data class Moto(
    @DocumentId
    val id: String = "",
    val marca: String = "",
    val modelo: String = "",
    val precio: String = "",
    val descripcion: String = "",
    val imageUrl: String = ""
)