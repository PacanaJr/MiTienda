package com.example.mitienda.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Routes : NavKey {

    @Serializable
    data object Login : Routes

    @Serializable
    data object Registro : Routes

    @Serializable
    data object Home : Routes

    @Serializable
    data class Detalle(val id: String) : Routes

    @Serializable
    data class Editar(val id: String) : Routes

    @Serializable
    data object Error : Routes
}