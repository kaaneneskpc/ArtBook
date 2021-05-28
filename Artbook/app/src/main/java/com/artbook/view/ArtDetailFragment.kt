package com.artbook.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.artbook.R
import com.artbook.databinding.FragmentArtDetailsBinding
import com.artbook.util.Status
import com.artbook.viewModel.ArtViewModel
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArtDetailFragment @Inject constructor(
    val glide : RequestManager
) : Fragment(R.layout.fragment_art_details) {

    lateinit var viewModel : ArtViewModel

    private var fragmentBinding : FragmentArtDetailsBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)
        val binding = FragmentArtDetailsBinding.bind(view)
        fragmentBinding=binding
        subscribeToObservers()

        binding.artImageView.setOnClickListener {
            findNavController().navigate(ArtDetailFragmentDirections.actionArtDetailFragmentToImageAPIFragment())
        }

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
               findNavController().popBackStack()
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
        binding.saveButton.setOnClickListener {
            viewModel.makeArt(binding.nameText.text.toString(),
                binding.artistName.text.toString(),
                binding.artistYear.text.toString())
        }

    }

    private fun subscribeToObservers(){
           viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer { url->
               fragmentBinding?.let{
                   glide.load(url).into(it.artImageView)
               }
           })
          viewModel.insertArtMsg.observe(viewLifecycleOwner, Observer {
              when(it.status){
                  Status.SUCCESS -> {
                      Toast.makeText(requireActivity(),"Success",Toast.LENGTH_LONG).show()
                      findNavController().popBackStack()
                      viewModel.resetInsertArtMessage()
                  }
                  Status.ERROR ->{
                      Toast.makeText(requireActivity(),it.message ?: "Error",Toast.LENGTH_LONG).show()
                  }
                  Status.LOADING ->{}
              }
          })
    }

    override fun onDestroyView() {
        fragmentBinding=null
        super.onDestroyView()

    }
}