package com.example.spotifylana.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifylana.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var rvAlbums: RecyclerView
    private lateinit var adapter: AlbumsAdapter
    private lateinit var firebaseAuth: FirebaseAuth
    private val progressDialog by lazy { CustomProgressDialog(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        bindView()
        bindViewModel()
    }

    override fun onStart() {
        super.onStart()
        progressDialog.start("")
        viewModel.onStart()
    }

    private fun bindView(){
        rvAlbums= findViewById(R.id.rvAlbums)
        rvAlbums.layoutManager= LinearLayoutManager(this)
        adapter = AlbumsAdapter()
        rvAlbums.adapter= adapter

        val star: ImageView = findViewById(R.id.imageView7)

        star.setOnClickListener {
            val intent = Intent(this, Favorite::class.java)
            startActivity(intent)
        }

    }
    private fun bindViewModel(){
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.albums.observe(this) {
            // Actualizar la lista de la pantalla
             adapter.Update(it)
             progressDialog.stop()
        }

        var albums = adapter.items
        for (album in albums){

        }
    }
    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            // Usuario no logueado
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}