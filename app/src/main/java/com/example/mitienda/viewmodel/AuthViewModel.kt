package com.example.mitienda.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _usuarioEmail = mutableStateOf(auth.currentUser?.email)
    val usuarioEmail: State<String?> = _usuarioEmail

    fun login(email: String, pass: String, onOk: () -> Unit, onError: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                _usuarioEmail.value = auth.currentUser?.email
                onOk()
            }
            .addOnFailureListener {
                onError(it.message ?: "Error al iniciar sesión")
            }
    }

    fun registro(email: String, pass: String, onOk: () -> Unit, onError: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                _usuarioEmail.value = auth.currentUser?.email
                onOk()
            }
            .addOnFailureListener {
                onError(it.message ?: "Error al registrar")
            }
    }

    fun logout() {
        auth.signOut()
        _usuarioEmail.value = null
    }
}