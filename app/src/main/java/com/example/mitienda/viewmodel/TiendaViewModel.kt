package com.example.mitienda.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mitienda.model.Producto
import com.google.firebase.firestore.FirebaseFirestore

class TiendaViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val coleccion = db.collection("productos")

    private val _productos = mutableStateOf<List<Producto>>(emptyList())
    val productos: State<List<Producto>> = _productos

    private val _cargando = mutableStateOf(true)
    val cargando: State<Boolean> = _cargando

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    init {
        coleccion.addSnapshotListener { snap, e ->
            if (e != null) {
                _error.value = e.message
                _cargando.value = false
                return@addSnapshotListener
            }
            _productos.value = snap?.toObjects(Producto::class.java) ?: emptyList()
            _error.value = null
            _cargando.value = false
        }
    }

    fun crearProducto(
        nombre: String,
        precio: String,
        descripcion: String,
        imageUrl: String,
        onOk: () -> Unit,
        onError: (String) -> Unit
    ) {
        val data = hashMapOf(
            "nombre" to nombre,
            "precio" to precio,
            "descripcion" to descripcion,
            "imageUrl" to imageUrl
        )

        coleccion.add(data)
            .addOnSuccessListener { onOk() }
            .addOnFailureListener { onError(it.message ?: "Error al crear") }
    }

    fun borrarProducto(id: String, onOk: () -> Unit, onError: (String) -> Unit) {
        coleccion.document(id).delete()
            .addOnSuccessListener { onOk() }
            .addOnFailureListener { onError(it.message ?: "Error al borrar") }
    }

    fun actualizarProducto(
        id: String,
        nombre: String,
        precio: String,
        descripcion: String,
        imageUrl: String,
        onOk: () -> Unit,
        onError: (String) -> Unit
    ) {
        val data = hashMapOf(
            "nombre" to nombre,
            "precio" to precio,
            "descripcion" to descripcion,
            "imageUrl" to imageUrl
        )

        coleccion.document(id).set(data)
            .addOnSuccessListener { onOk() }
            .addOnFailureListener { onError(it.message ?: "Error al actualizar") }
    }

    fun getProducto(id: String): Producto? = _productos.value.firstOrNull { it.id == id }
}