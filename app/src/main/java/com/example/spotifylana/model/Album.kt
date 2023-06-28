package com.example.spotifylana.model

data class Album (
    val total_tracks: Int,
    val name: String,
    val release_date: String,
    val images: ArrayList<Image>

    )
