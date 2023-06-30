package com.example.spotifylana.data

import android.content.Context
import android.util.Log
import com.example.spotifylana.model.Album
import com.example.spotifylana.model.Image
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
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
            Log.d(_TAG,"resultado exitoso")
            Log.d(_TAG, result.body()?.items?.size.toString())
            result.body()?.items ?: ArrayList<Album>()
            //Log.d(_TAG, it.size.toString)

        }else{
            Log.e(_TAG, "Error en llamado API: " + result.message())
            ArrayList<Album>()
        }

    }
    fun getFavAlbums (context: Context, callback: (ArrayList<Album>) -> Unit) {

        FirebaseApp.initializeApp(context)
        val database = FirebaseFirestore.getInstance()
        val albums = ArrayList<Album>()

        database.collection("favoritos").get().addOnSuccessListener() {
            result ->
                    for (document in result) {
                        // Accede a los datos de cada documento
                        val fav = document.data
                        // Haz algo con los datos del documento
                        Log.d("DATASOURCE", "Favoritos traido")

                        val album = Album(
                            fav["total_tracks"] as? Int ?: 0,
                            fav["name"] as? String ?: "",
                            fav["release_date"] as? String ?: "",
                            fav["images"] as? ArrayList<Image> ?: ArrayList(),
                            fav["fav"] as? Boolean ?: false
                        )

                        albums.add(album)
                        Log.d("DATASOURCE", album.toString())
                        Log.d("DATASOURCE", albums.toString())
                        Log.d("DATASOURCE", "Favoritos Exitoso")
                    }
                    callback(albums)
        } .addOnFailureListener { e ->
            // Maneja el error de obtener los documentos
            Log.d("DATASOURCE","Error al obtener los documentos de la colección: $e")
        }

    }
}