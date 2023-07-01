package com.example.spotifylana.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.ColorFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spotifylana.R
import com.example.spotifylana.model.Album
import com.google.firebase.firestore.FirebaseFirestore

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

        // Base de datos
        val db = FirebaseFirestore.getInstance()
        db.collection("favoritos").document("Lust For Life").get().addOnSuccessListener {
            Log.i("firebaseeeeeeeee", "GOT VALUE ${it.get("total_tracks")}")
        }
        // Favorito
        var favv: Any?= null
        db.collection("favoritos").document(items[position].name).get().addOnSuccessListener {
             var favv= it.get("fav")
            if (items[position].fav || favv.toString() == "true"){
                holder.star.setColorFilter(Color.YELLOW)
                Log.d("TRACKS EN ALBUM ADAPTER", it.get("total_tracks").toString())
            } else {
                holder.star.clearColorFilter() }
        }
        // Click en la estrella Fav
        holder.star.setOnClickListener {
            if (items[position].fav || favv.toString() == "true") {
                holder.star.clearColorFilter()
                items[position].fav = false
                db.collection("favoritos").document(items[position].name).delete()
            } else {
                holder.star.setColorFilter(Color.YELLOW)
                items[position].fav = true
                Log.d("TOTAL TRACKS AL AGREGAR FAV", items[position].total_tracks.toString())
                db.collection("favoritos").document(items[position].name).set(
                    hashMapOf(
                        "totalTracks" to items[position].total_tracks,
                        "name" to items[position].name,
                        "releaseDate" to items[position].release_date,
                        "images" to items[position].images,
                        "fav" to items[position].fav
                    )
                )
            }

           }

        /// Click en album Item.
        holder.itemView.setOnClickListener {
            val nombre = items[position].name
            val fecha = items[position].release_date
            val imagen = items[position].images[0].url
            val totalCanciones = items[position].total_tracks.toString()

        // Inicio de la otra activity
            val intentt = Intent(holder.itemView.context, SingleAlbum::class.java)
            intentt.putExtra("nombre",nombre)
            intentt.putExtra("fecha", fecha)
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