package com.artbook.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.artbook.adapter.ArtRecyclerAdapter
import com.artbook.adapter.ImageRecyclerAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
     private val glide : RequestManager,
     private val artRecyclerAdapter : ArtRecyclerAdapter,
     private val imageRecyclerAdapter : ImageRecyclerAdapter
):FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ImageAPIFragment::class.java.name -> ImageAPIFragment(imageRecyclerAdapter)
            ArtFragment::class.java.name ->ArtFragment(artRecyclerAdapter)
            ArtDetailFragment::class.java.name -> ArtDetailFragment(glide)
            else -> super.instantiate(classLoader, className)
        }




    }
}