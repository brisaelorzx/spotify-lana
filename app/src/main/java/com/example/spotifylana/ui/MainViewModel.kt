package com.example.spotifylana.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spotifylana.data.AlbumsRepository
import com.example.spotifylana.model.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlin.coroutines.CoroutineContext

class MainViewModel: ViewModel() {
    // Constantes
    private val _TAG = "API-DEMO"
    private val coroutineContext: CoroutineContext = newSingleThreadContext("uadedemo")
    private val scope = CoroutineScope(coroutineContext)

    // Dependencias
    private val albumsRepo = AlbumsRepository()


    // Propiedades
    var albums = MutableLiveData<ArrayList<Album>>()
    var artist = "00FQb4jTyendYWaN8pK0wa"
    var token = "Bearer BQANekDi-OIhn6etkIIF73yUctD4v8FkrUHptMyfeED6TlC9baPwK84T74UTKWcbTs_Rf8xGB8wYKdODKo5fryNdqQQkRzJFLy2r8bqTtZmxffCICGg"
    // Funciones
    fun onStart() {
        // Cargar los datos de los albums
        scope.launch {
            kotlin.runCatching {
                albumsRepo.getAlbums(artist, token)
            }.onSuccess {
                Log.d(_TAG, "Albums onSucces")
                albums.postValue(it)
                Log.d(_TAG, "capturo la info")
            }.onFailure {
                Log.e(_TAG, "Albums error" + it)
            }
        }
    }
}