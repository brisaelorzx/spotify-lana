package com.example.spotifylana.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spotifylana.R
import com.example.spotifylana.model.Album

class AlbumsAdapter: RecyclerView.Adapter<AlbumViewHolder>() {
    var items: MutableList<Album> = ArrayList<Album>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        return AlbumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        var url: String = items[position].images[2].url
        holder.name.text = items[position].name
        Glide.with(holder.itemView.context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image)
        ///...
        holder.itemView.setOnClickListener {
            val nombre = items[position].name
            val fecha = items[position].release_date
            val generos = items[position].genres
            val imagen = items[position].images[1].url
            val totalCanciones = items[position].total_tracks.toString()

        // Inicio de la otra activity

            val intentt = Intent(holder.itemView.context, SingleAlbum::class.java)
            intentt.putExtra("nombre",nombre)
            intentt.putExtra("fecha", fecha)
            intentt.putExtra("generos", generos)
            intentt.putExtra("imagen", imagen)
            intentt.putExtra("totalCanciones", totalCanciones)

            holder.itemView.context.startActivity(intentt)



        }


    }

    fun Update (lista: MutableList<Album>){
        items = lista
        this.notifyDataSetChanged()
    }

}