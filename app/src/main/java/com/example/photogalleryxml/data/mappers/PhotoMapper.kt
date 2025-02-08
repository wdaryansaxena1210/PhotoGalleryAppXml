package com.example.photogalleryxml.data.mappers

import com.example.photogalleryxml.data.remote.PhotosDto
import com.example.photogalleryxml.domain.photos.PhotoData
import com.example.photogalleryxml.domain.photos.PhotosData

fun PhotosDto.toPhotosData(): PhotosData {
    val photos = mutableListOf<PhotoData>()
    
    for (i in 0 until this.photos!!.size){
        val currentPhoto = this.photos[i]
        
        val tempPhoto = PhotoData(
            author = currentPhoto.author,
            download_url = currentPhoto.download_url,
            height = currentPhoto.height,
            id = currentPhoto.id,
            url = currentPhoto.url,
            width = currentPhoto.width,
        )
        photos.add(tempPhoto)
    }

    val photosData = PhotosData(photos)

    return photosData
}