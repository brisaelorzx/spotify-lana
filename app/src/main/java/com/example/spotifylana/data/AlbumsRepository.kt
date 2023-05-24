package com.example.spotifylana.data

import android.util.Log
import com.example.spotifylana.model.Album

class AlbumsRepository {
    private val albumsDS = AlbumsDataSource()
    private val _TAG = "API-DEMO"

    suspend fun getAlbums(artist: String, token: String): ArrayList<Album>{
        Log.d(_TAG, "En Repository va bien la request")
        return albumsDS.getAlbums(artist, token)

    }
}