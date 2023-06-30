package com.example.spotifylana.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spotifylana.data.AlbumsRepository
import com.example.spotifylana.model.Album
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlin.coroutines.CoroutineContext


class FavoriteViewModel: ViewModel() {

    val albums: MutableLiveData<ArrayList<Album>> = MutableLiveData()
    // Constantes
    private val coroutineContext: CoroutineContext = newSingleThreadContext("uadedemo")
    private val scope = CoroutineScope(coroutineContext)

    // Dependencias
    private val albumsRepo = AlbumsRepository()

    fun onStart(context : Context, callback : (ArrayList<Album>) -> Unit) {
        // Cargar los datos de los equipos
        scope.launch {
            kotlin.runCatching {
                albumsRepo.getFavAlbums(context, callback)
            }.onSuccess {
                Log.d("FAVORITE VIEW MODEL", "Albums Fav on SuccesS" + it.toString())
            }.onFailure {
                Log.e("FAVORITE VIEW MODEL", "FAVORITE Error: " + it)
            }
        }
    }
}