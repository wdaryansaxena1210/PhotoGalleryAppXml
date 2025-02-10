package com.example.photogalleryxml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photogalleryxml.adapter.PhotoAdapter
import com.example.photogalleryxml.data.mappers.toPhotosData
import com.example.photogalleryxml.data.remote.PhotoAPI
import com.example.photogalleryxml.data.remote.PhotosDto
import com.example.photogalleryxml.domain.photos.PhotosData
import com.example.photogalleryxml.presentation.PhotoViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val photoApi = PhotoAPI(this)
        var mostViewedPhotos: PhotosDto? = null
        var mostRecentPhotos: PhotosDto? = null

        lateinit var recyclerView: RecyclerView
        val viewmodel = PhotoViewModel(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            viewmodel.state.collect { state ->
                if (!state.isLoading) {
                    println("ISLOADING is false")
                    println("mostViewedPhotosState:" + state.mostViewedPhotos)
                    println(state.mostRecentPhotos)

                    recyclerView = findViewById(R.id.ColumnRecyclerView)
                    recyclerView.setHasFixedSize(true)
                    recyclerView.adapter = PhotoAdapter(state.mostViewedPhotos!!)
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                }
            }
        }


//        recyclerView = findViewById(R.id.ColumnRecyclerView)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = mostViewedPhotos?.let { PhotoAdapter(it) }
//        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }
}