package com.example.photogalleryxml.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photogalleryxml.R
import com.example.photogalleryxml.adapter.PhotoAdapter
import kotlinx.coroutines.launch

class MostRecentPhotosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: PhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_most_recent_photos, container, false)

        recyclerView = view.findViewById(R.id.ColumnRecyclerView) // Find RecyclerView inside Fragment
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel = PhotoViewModel(requireContext())

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (!state.isLoading) {
                    recyclerView.adapter = PhotoAdapter(state.mostRecentPhotos!!)
                }
            }
        }
        return view
    }
}