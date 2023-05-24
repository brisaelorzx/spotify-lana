package com.example.spotifylana.data

import com.example.spotifylana.model.Album
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumsAPI {
    @GET("{id}/albums")
    fun getAlbums(
        @Header ("Authorization") token: String,
        @Path("id") artist: String

    ): Call<ArrayList<Album>>
}