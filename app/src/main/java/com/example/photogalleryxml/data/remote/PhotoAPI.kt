package com.example.photogalleryxml.data.remote

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class PhotoAPI(context: Context) {
    val queue = Volley.newRequestQueue(context)
    val url1 = "https://picsum.photos/v2/list?page=1&limit=10"
    val url2 = "https://picsum.photos/v2/list?page=2&limit=10"
    var mostViewedPhotosDto = PhotosDto()
    var mostRecentPhotosDto = PhotosDto()


    suspend fun getMostViewedPhotos(): PhotosDto {
        println("fetching most viewed photos")

        val jsonRequest = JsonArrayRequest(
            Request.Method.GET,
            url1,
            null,
            {response ->
                println("entering most viewed photos response callback")
                println(response)
                val photos = mutableListOf<PhotoDto>()

                for(i in 0 until response.length()){

                    val currentPhoto = response.getJSONObject(i)

                    photos.add(
                        PhotoDto(
                            author = currentPhoto.getString("author"),
                            download_url = currentPhoto.getString("download_url"),
                            height = currentPhoto.getString("height").toInt(),
                            id = currentPhoto.getString("id"),
                            url = currentPhoto.getString("url"),
                            width = currentPhoto.getString("width").toInt()
                        )
                    )
                }

                println(photos)

                mostViewedPhotosDto = PhotosDto(photos = photos)

            },
            {error ->
                println("Error: $error")
            }
        )

        queue.add(jsonRequest)
        println("most viewed photos fetched")

        println(mostViewedPhotosDto)
        return mostViewedPhotosDto
    }


    suspend fun getMostRecentPhotos(): PhotosDto {
        println("fetching most recent photos")

        val jsonRequest = JsonArrayRequest(
            Request.Method.GET,
            url2,
            null,
            {response ->
                println("entering most recent photos response callback")
                println(response)
                val photos = mutableListOf<PhotoDto>()

                for(i in 0 until response.length()){

                    val currentPhoto = response.getJSONObject(i)

                    photos.add(
                        PhotoDto(
                            author = currentPhoto.getString("author"),
                            download_url = currentPhoto.getString("download_url"),
                            height = currentPhoto.getString("height").toInt(),
                            id = currentPhoto.getString("id"),
                            url = currentPhoto.getString("url"),
                            width = currentPhoto.getString("width").toInt()
                        )
                    )
                }

                mostRecentPhotosDto = PhotosDto(photos = photos)

            },
            {error ->
                println("Error: $error")
            }
        )

        queue.add(jsonRequest)
        println("most recent photos fetched")

        return mostRecentPhotosDto
    }
}