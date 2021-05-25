package com.artbook.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.artbook.R
import com.artbook.adapter.ImageRecyclerAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ImageAPIFragment @Inject constructor(
    private val imageRecyclerAdapter : ImageRecyclerAdapter
) : Fragment(R.layout.fragment_image_api) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}