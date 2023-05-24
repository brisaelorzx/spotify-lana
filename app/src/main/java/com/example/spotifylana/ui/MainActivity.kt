package com.example.spotifylana.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.example.spotifylana.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViewModel()

        Handler(Looper.getMainLooper()).postDelayed({
            var intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            finish()

        },4000)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }
    private fun bindViewModel(){
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.albums.observe(this) {
            // Actualizar la lista de la pantalla

        }
    }
}