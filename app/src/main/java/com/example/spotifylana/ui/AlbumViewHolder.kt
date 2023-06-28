package com.example.spotifylana.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifylana.R


class AlbumViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.lblName)
    val image: ImageView = itemView.findViewById(R.id.imageView3)
    val star: ImageView = itemView.findViewById(R.id.imageView8)
}