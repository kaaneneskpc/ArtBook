package com.artbook.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.artbook.R
import com.artbook.databinding.FragmentArtsBinding

class ArtFragment : Fragment(R.layout.fragment_arts) {

    private var fragmentBinding : FragmentArtsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding  = FragmentArtsBinding.bind(view)
        fragmentBinding = binding

        binding.fabArtAdd.setOnClickListener{
            findNavController().navigate(ArtDetailFragmentDirections.actionArtDetailFragmentToImageAPIFragment())
        }





    }

    override fun onDestroyView() {
        fragmentBinding=null
        super.onDestroyView()

    }
}