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
    // Constante
    private val _TAG = "API-DEMO"
    private val coroutineContext: CoroutineContext = newSingleThreadContext("uadedemo")
    private val scope = CoroutineScope(coroutineContext)

    // Dependencias
    private val albumsRepo = AlbumsRepository()


    // Propiedades
    var albums = MutableLiveData<ArrayList<Album>>()
    var artist = "00FQb4jTyendYWaN8pK0wa"
    var token = "Bearer BQCOVGqtQK1WhKZ3-j5YyMgPs2JIvzYBYDZY1zH5nVUVHRml2a0KRtAkbkGDLYp1_81ztujJ4waIaemjfVnE-cf-WUP8opx34Nx8N61uJ6w3dB31uqk"

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