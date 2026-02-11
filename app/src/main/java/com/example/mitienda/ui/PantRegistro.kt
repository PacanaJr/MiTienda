package com.example.mitienda.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun PantRegistro(
    onRegistroOk: () -> Unit,
    onCancelar: () -> Unit,
    authViewModel: AuthViewModel
) {
    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val pass2 = remember { mutableStateOf("") }
    val error = remember { mutableStateOf<String?>(null) }
    val cargando = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Registro de usuario/a")

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

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = pass2.value,
            onValueChange = { pass2.value = it },
            label = { Text("Repite contraseña") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = {
                error.value = null
                if (email.value.isBlank() || pass.value.isBlank() || pass2.value.isBlank()) {
                    error.value = "Rellena todos los campos"
                    return@Button
                }
                if (pass.value != pass2.value) {
                    error.value = "Las contraseñas no coinciden"
                    return@Button
                }
                if (cargando.value) return@Button
                cargando.value = true

                authViewModel.registro(
                    email = email.value.trim(),
                    pass = pass.value,
                    onOk = {
                        cargando.value = false
                        onRegistroOk()
                    },
                    onError = {
                        cargando.value = false
                        error.value = it
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (cargando.value) "Registrando..." else "Registrarte")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = onCancelar, modifier = Modifier.fillMaxWidth()) {
            Text("Cancelar")
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (error.value != null) {
            Text(error.value ?: "")
        }
    }
}