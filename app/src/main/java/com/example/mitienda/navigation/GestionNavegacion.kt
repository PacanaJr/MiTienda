package com.example.mitienda.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.mitienda.ui.PantConfirmacion
import com.example.mitienda.ui.PantDetalles
import com.example.mitienda.ui.PantHome
import kotlinx.serialization.Serializable

sealed interface Routes : NavKey {

    @Serializable
    data object Home : Routes

    @Serializable
    data class Detalle(val id: String) : Routes

    @Serializable
    data class Confirmacion(val id: String) : Routes

    @Serializable
    data object Error : Routes
}

@Composable
fun GestionNavegacion() {
    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Home> {
                PantHome(
                    navegaADetalle = { id -> backStack.add(Routes.Detalle(id)) }
                )
            }
            entry<Routes.Detalle> { key ->
                PantDetalles(
                    id = key.id,
                    navegaAConfirmacion = { id -> backStack.add(Routes.Confirmacion(id)) },
                    navegaAtras = { backStack.removeLastOrNull() }
                )
            }
            entry<Routes.Confirmacion> { key ->
                PantConfirmacion(
                    id = key.id,
                    vuelveHome = {
                        backStack.removeLastOrNull()
                        backStack.removeLastOrNull()
                    },
                    navegaAtras = { backStack.removeLastOrNull() }
                )
            }
            entry<Routes.Error> {
                Text("Error")
            }
        }
    )
}
