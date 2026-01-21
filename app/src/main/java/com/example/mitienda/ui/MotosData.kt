package com.example.mitienda.ui

import androidx.annotation.DrawableRes
import com.example.mitienda.R

data class MotoItem(
    val id: String,
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    @DrawableRes val imagenRes: Int
)

object MotosData {
    val motos = listOf(
        MotoItem(
            id = "ninja400",
            nombre = "Kawasaki Ninja 400",
            precio = 5899.0,
            descripcion = "Deportiva ligera, ideal para empezar y para ciudad. Muy manejable.",
            imagenRes = R.drawable.kawasaki_png
        ),
        MotoItem(
            id = "mt07",
            nombre = "Yamaha MT-07",
            precio = 7899.0,
            descripcion = "Naked divertida, par motor muy bueno, cómoda y polivalente.",
            imagenRes = R.drawable.yamaha_png
        ),
        MotoItem(
            id = "cb500f",
            nombre = "Honda CB500F",
            precio = 6799.0,
            descripcion = "Equilibrada y fiable, perfecta para el A2 y uso diario.",
            imagenRes = R.drawable.honda
        )
    )

    fun getById(id: String): MotoItem? = motos.firstOrNull { it.id == id }
}
