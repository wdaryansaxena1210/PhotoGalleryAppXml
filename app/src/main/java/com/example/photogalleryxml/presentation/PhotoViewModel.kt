package com.example.photogalleryxml.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photogalleryxml.data.remote.PhotoAPI
import com.example.photogalleryxml.data.remote.PhotosDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoViewModel(applicationContext: Context) : ViewModel(){
    private val photoApi = PhotoAPI(applicationContext)

    private val _state = MutableStateFlow(PhotoState())
    val state = _state.asStateFlow()

    init {
        // Launch a coroutine in viewModelScope
        viewModelScope.launch {
            try {
                // Fetch photos
                photoApi.fetchMostViewedPhotos()
                photoApi.fetchMostRecentPhotos()

                delay(2000)

                val mostViewedPhotos = photoApi.getMostViewedPhotos()
                val mostRecentPhotos = photoApi.getMostRecentPhotos()

                _state.value = state.value.copy(
                    isLoading = false,
                    mostViewedPhotos = mostViewedPhotos,
                    mostRecentPhotos = mostRecentPhotos
                )

            } catch (e: Exception) {
                println("error in PhotoViewModel init block")
                e.printStackTrace()
            }
        }
    }
}