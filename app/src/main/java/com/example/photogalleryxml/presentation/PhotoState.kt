package com.example.photogalleryxml.presentation

import com.example.photogalleryxml.data.remote.PhotosDto

data class PhotoState(
    val isLoading : Boolean = true,
    val mostViewedPhotos: PhotosDto? = null,
    val mostRecentPhotos: PhotosDto? = null,
    val error : String? = null
)