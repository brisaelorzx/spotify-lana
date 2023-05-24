package com.example.spotifylana.model
import com.google.gson.annotations.SerializedName
import java.util.Objects

data class Album (
    /*
    val album_type: String,
    val total_tracks: Int,
    val available_markets: ArrayList<String>,
    val external_urls: ArrayList<String>,
    val href: String,
    val id: String,
    val images: ArrayList<Image>,
     */
    val name: String,
    val release_date_precision: String,
    val genres: ArrayList<String>)
