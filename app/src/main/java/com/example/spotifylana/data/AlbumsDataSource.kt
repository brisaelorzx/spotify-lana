package com.example.spotifylana.data

import android.util.Log
import com.example.spotifylana.model.Album
import com.google.firebase.firestore.FirebaseFirestore
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
        var result = api.getAlbums(token, artist).execute()

        return if (result.isSuccessful){
            /*
            val db = FirebaseFirestore.getInstance()

            db.collection("albums").document("id").set(
                hashMapOf(
                    "name" to "contenido variable",
                    "email" to "contenido variable 2"
                    )
            )

            db.collection("universities") .document("id").get().addOnSuccessListener {
                var nombre = it.get("name")
            }
            */

            Log.d(_TAG,"resultado exitoso")
            Log.d(_TAG, result.body()?.items?.size.toString())
            result.body()?.items ?: ArrayList<Album>()
            //Log.d(_TAG, it.size.toString)

        }else{
            Log.e(_TAG, "Error en llamado API: " + result.message())
            ArrayList<Album>()
        }

    }
}