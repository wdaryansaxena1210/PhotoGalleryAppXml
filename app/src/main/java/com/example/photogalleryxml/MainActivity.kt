package com.example.photogalleryxml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photogalleryxml.adapter.PhotoAdapter
import com.example.photogalleryxml.data.mappers.toPhotosData
import com.example.photogalleryxml.data.remote.PhotoAPI
import com.example.photogalleryxml.data.remote.PhotosDto
import com.example.photogalleryxml.databinding.ActivityMainBinding
import com.example.photogalleryxml.domain.photos.PhotosData
import com.example.photogalleryxml.presentation.MostRecentPhotosFragment
import com.example.photogalleryxml.presentation.MostViewedPhotosFragment
import com.example.photogalleryxml.presentation.PhotoViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var mostViewedPhotosFragment: MostViewedPhotosFragment
    private lateinit var mostRecentPhotosFragment: MostRecentPhotosFragment
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewmodel: PhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        mostViewedPhotosFragment = MostViewedPhotosFragment()
        mostRecentPhotosFragment = MostRecentPhotosFragment()
        viewmodel = PhotoViewModel(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.most_viewed_photos_fragment -> replaceFragment(mostViewedPhotosFragment)
                R.id.most_recent_photos_fragment -> replaceFragment(mostRecentPhotosFragment)
            }
            true
        }



        lifecycleScope.launch {
            viewmodel.state.collect { state ->

            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}