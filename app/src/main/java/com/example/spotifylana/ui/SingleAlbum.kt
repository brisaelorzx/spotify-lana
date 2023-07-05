package com.example.spotifylana.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.spotifylana.R

class SingleAlbum: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_album)
        bindView()

        // Obtener los datos pasados desde la actividad anterior
        val nombre = intent.getStringExtra("nombre")
        val fecha = intent.getStringExtra("fecha")
        val generos = intent.getStringExtra("generos")
        val imagen = intent.getStringExtra("imagen")
        val totalCanciones = intent.getStringExtra("totalCanciones")
        val fav = intent.getStringExtra("fav")


        // Utilizar los datos obtenidos en tu actividad
        val img = findViewById<ImageView>(R.id.imageView10)
        val textView4 = findViewById<TextView>(R.id.textView4)
        val textFecha = findViewById<TextView>(R.id.textFecha)
        val textView10 = findViewById<TextView>(R.id.textView10)
        val favv = findViewById<ImageView>(R.id.imageView12)

        Glide.with(this)
            .load(imagen)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(img)

        textView4.text = nombre
        textFecha.text= fecha
        textView10.text= totalCanciones

        if (fav.equals("true")) {
            favv.setColorFilter(Color.YELLOW)
        }


    }

    private fun bindView (){
        val listOfAlbums: ImageView = findViewById(R.id.imageView6)
        val listOfFavs: ImageView = findViewById(R.id.imageView7)
        val botonVolver = findViewById<ImageView>(R.id.botonBack)

        listOfAlbums.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        listOfFavs.setOnClickListener {
            val intent = Intent(this, Favorite::class.java)
            startActivity(intent)
        }

        botonVolver.setOnClickListener {
            finish()
        }

    }
}