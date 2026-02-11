package com.example.mitienda.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mitienda.viewmodel.AuthViewModel

@Composable
fun PantLogin(
    onLoginOk: () -> Unit,
    onRegistro: () -> Unit,
    authViewModel: AuthViewModel
) {
    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val error = remember { mutableStateOf<String?>(null) }
    val cargando = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Login")

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = pass.value,
            onValueChange = { pass.value = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = {
                error.value = null
                if (email.value.isBlank() || pass.value.isBlank()) {
                    error.value = "Rellena todos los campos"
                    return@Button
                }
                if (cargando.value) return@Button
                cargando.value = true

                authViewModel.login(
                    email = email.value.trim(),
                    pass = pass.value,
                    onOk = {
                        cargando.value = false
                        onLoginOk()
                    },
                    onError = {
                        cargando.value = false
                        error.value = it
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (cargando.value) "Entrando..." else "Entrar")
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (error.value != null) {
            Text(error.value ?: "")
            Spacer(modifier = Modifier.height(10.dp))
        }

        Row {
            Text("¿No tienes cuenta? ")
            Text("Regístrate", modifier = Modifier.clickable { onRegistro() })
        }
    }
}