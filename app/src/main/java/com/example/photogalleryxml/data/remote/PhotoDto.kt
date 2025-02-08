package com.example.photogalleryxml.data.remote

data class PhotoDto(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)