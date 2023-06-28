package com.example.spotifylana.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.spotifylana.R

class SingleAlbum: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_album)

        // Obtener los datos pasados desde la actividad anterior
        val nombre = intent.getStringExtra("nombre")
        val fecha = intent.getStringExtra("fecha")
        val generos = intent.getStringExtra("generos")
        val imagen = intent.getStringExtra("imagen")
        val totalCanciones = intent.getStringExtra("totalCanciones")


        // Utilizar los datos obtenidos en tu actividad
        val img = findViewById<ImageView>(R.id.imageView10)
        val textView4 = findViewById<TextView>(R.id.textView4)
        val textFecha = findViewById<TextView>(R.id.textFecha)
        val textView10 = findViewById<TextView>(R.id.textView10)
        val botonVolver = findViewById<ImageView>(R.id.botonBack)

        Glide.with(this)
            .load(imagen)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(img)

        textView4.text = nombre
        textFecha.text= fecha
        textView10.text= totalCanciones

        botonVolver.setOnClickListener {
            finish()
        }
    }
}