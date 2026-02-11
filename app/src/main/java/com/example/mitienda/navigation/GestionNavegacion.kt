package com.example.mitienda.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.mitienda.ui.PantDetalles
import com.example.mitienda.ui.PantEditarProducto
import com.example.mitienda.ui.PantHome
import com.example.mitienda.ui.PantLogin
import com.example.mitienda.ui.PantRegistro
import com.example.mitienda.viewmodel.AuthViewModel
import com.example.mitienda.viewmodel.TiendaViewModel

@Composable
fun GestionNavegacion() {
    val authViewModel: AuthViewModel = viewModel()
    val tiendaViewModel: TiendaViewModel = viewModel()

    val start = if (authViewModel.usuarioEmail.value != null) Routes.Home else Routes.Login
    val backStack = rememberNavBackStack(start)

    fun vuelveALogin() {
        while (backStack.isNotEmpty()) backStack.removeLastOrNull()
        backStack.add(Routes.Login)
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<Routes.Login> {
                PantLogin(
                    onLoginOk = { backStack.add(Routes.Home) },
                    onRegistro = { backStack.add(Routes.Registro) },
                    authViewModel = authViewModel
                )
            }

            entry<Routes.Registro> {
                PantRegistro(
                    onRegistroOk = { backStack.removeLastOrNull() },
                    onCancelar = { backStack.removeLastOrNull() },
                    authViewModel = authViewModel
                )
            }

            entry<Routes.Home> {
                PantHome(
                    navegaADetalle = { id -> backStack.add(Routes.Detalle(id)) },
                    navegaAEditar = { id -> backStack.add(Routes.Editar(id)) },
                    onLogout = {
                        authViewModel.logout()
                        vuelveALogin()
                    },
                    tiendaViewModel = tiendaViewModel,
                    authViewModel = authViewModel
                )
            }

            entry<Routes.Detalle> { key ->
                PantDetalles(
                    id = key.id,
                    navegaAtras = { backStack.removeLastOrNull() },
                    tiendaViewModel = tiendaViewModel
                )
            }

            entry<Routes.Editar> { key ->
                PantEditarProducto(
                    id = key.id,
                    navegaAtras = { backStack.removeLastOrNull() },
                    tiendaViewModel = tiendaViewModel
                )
            }

            entry<Routes.Error> {
                Text("Error")
            }
        }
    )
}