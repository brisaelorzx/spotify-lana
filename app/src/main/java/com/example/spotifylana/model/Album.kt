package com.example.spotifylana.model
import com.google.gson.annotations.SerializedName
import java.util.Objects

data class Album (
    val total_tracks: Int,
    val name: String,
    val release_date_precision: String,
    val genres: ArrayList<String>,
    val images: ArrayList<Image>)
