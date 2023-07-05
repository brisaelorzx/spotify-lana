package com.example.spotifylana.ui
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifylana.R
import com.example.spotifylana.model.Album


class Favorite : AppCompatActivity() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var rvFav: RecyclerView
    private lateinit var adapter: FavoriteAdapter
    private lateinit var albums: ArrayList<Album>
    val searchResults = ArrayList<Album>()  // Lista para almacenar los resultados de búsqueda

    private val progressDialog by lazy { CustomProgressDialog(this) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        albums = ArrayList()
        bindView()
        bindViewModel()
    }

    override fun onStart() {
        super.onStart()
        progressDialog.start("")
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        viewModel.onStart(this) {album ->
            albums = album
            viewModel.albums.value = albums
            Log.d("FAVORITE ACTIVITY", albums.toString())
        }


    }

    private fun bindView() {
        rvFav = findViewById(R.id.rvFav)
        rvFav.layoutManager = LinearLayoutManager(this)
        adapter = FavoriteAdapter()
        rvFav.adapter= adapter

        val listOfAlbums: ImageView = findViewById(R.id.imageView6)

        listOfAlbums.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val searchView = findViewById<SearchView>(R.id.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Aquí puedes realizar la búsqueda cuando se envía el texto de búsqueda
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Aquí puedes realizar la búsqueda mientras el usuario escribe el texto
                search(newText)
                return true
            }
        })


    }

    private fun search(query: String) {
        searchResults.clear()  // Limpiar los resultados de búsqueda anteriores

        for (album in albums) {
            if (album.name.contains(query, ignoreCase = true)) {
                searchResults.add(album)  // Agregar álbumes que coincidan con el nombre buscado
            }
        }

        viewModel.albums.value= searchResults

        // Aquí puedes actualizar la interfaz de usuario con los resultados de búsqueda
        // Por ejemplo, puedes mostrar los resultados en un RecyclerView o ListView
        // utilizando un adaptador personalizado.
    }


    private fun bindViewModel(){
        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        viewModel.albums.observe(this){
            // Actualizar la lista de la pantalla
            adapter.Update(it)
            progressDialog.stop()
        }
    }




    }
