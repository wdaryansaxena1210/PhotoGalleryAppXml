package com.example.photogalleryxml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.photogalleryxml.data.remote.PhotoAPI
import com.example.photogalleryxml.data.remote.PhotosDto
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val photoApi = PhotoAPI(this)
        lateinit var photos: PhotosDto


        lifecycleScope.launch {
            println("MOST VIEWED PHOTOS")
            photos = photoApi.getMostViewedPhotos()
            println(photos)
        }
    }
}