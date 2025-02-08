package com.example.photogalleryxml.domain.photos

data class PhotoData(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)
