package com.example.photogalleryxml.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.photogalleryxml.R
import com.example.photogalleryxml.data.remote.PhotoDto
import com.example.photogalleryxml.data.remote.PhotosDto
import com.squareup.picasso.Picasso

class PhotoAdapter(private val data: PhotosDto) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>(){

    //class (data) : RecyclerView.Adapter< >()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //attach a new "itemView" of the type "R.layout.photo_layout",  to the parent view "ColumnRecyclerView"
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.photo_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.photos?.size ?: 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //in the viewholder part, you extracted the thing you need to update. now actually update it to its
        //correct value
        println("onBindViewHolder $data")
        val currentPhoto = data.photos!![position]


        Picasso.get()
            .load(currentPhoto.download_url)  // Load the image from the URL
            .into(holder.rvImage)

        holder.rvText.text = currentPhoto.author ?: "Dummy Author"
    }


    class ViewHolder(photoView : View) : RecyclerView.ViewHolder(photoView) {
        //take a view and EXTRACT its INNER elements INTO variables and store them so we can:
        //change their values. basically extract the elements whose value needs to be updated
        val rvImage : ImageView = photoView.findViewById<ImageView>(R.id.photo)
        val rvText : TextView = photoView.findViewById<TextView>(R.id.author)
    }

    val dummyData = PhotosDto(
        photos = listOf(
            PhotoDto(
                author = "Alejandro Escamilla",
                download_url = "https://picsum.photos/id/0/5000/3333",
                height = 3333,
                id = "0",
                url = "https://unsplash.com/photos/yC-Yzbqy7PY",
                width = 5000
            ),
            PhotoDto(
                author = "Alejandro Escamilla",
                download_url = "https://picsum.photos/id/0/5000/3333",
                height = 3333,
                id = "0",
                url = "https://unsplash.com/photos/yC-Yzbqy7PY",
                width = 5000
            ),
            PhotoDto(
                author = "Alejandro Escamilla",
                download_url = "https://picsum.photos/id/0/5000/3333",
                height = 3333,
                id = "0",
                url = "https://unsplash.com/photos/yC-Yzbqy7PY",
                width = 5000
            )
        )
    )


}