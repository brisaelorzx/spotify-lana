package com.example.spotifylana.data

import android.util.Log
import com.example.spotifylana.model.Album
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumsDataSource {
    val _BASE_URL = "https://api.spotify.com/v1/artists/"
    val _TAG = "API-DEMO"

    suspend fun getAlbums(artist: String, token: String): ArrayList<Album>{
            Log.d(_TAG, "Albums DataSource Get")
        val api = Retrofit.Builder()
            .baseUrl(_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AlbumsAPI::class.java)
        var result = api.getAlbums(artist, token).execute()

        return if (result.isSuccessful){
            Log.d(_TAG,"resultado exitoso")
            result.body() ?: ArrayList<Album>()
            //Log.d(_TAG, it.size.toString)

        }else{
            Log.e(_TAG, "Error en llamado API: " + result.message())
            ArrayList<Album>()
        }

    }
}