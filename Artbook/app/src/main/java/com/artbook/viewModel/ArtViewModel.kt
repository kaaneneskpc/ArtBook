package com.artbook.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbook.model.Art
import com.artbook.model.ImageResponse
import com.artbook.repository.ArtRepositoryInterface
import com.artbook.util.Resource
import kotlinx.coroutines.launch

class ArtViewModel @ViewModelInject constructor(
    private val  repository : ArtRepositoryInterface
) : ViewModel() {

    //ArtFragment

    val artList = repository.getArt()

    //ImageAPIFragment

    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList : LiveData<Resource<ImageResponse>>
    get() = images

    private val selectedImages = MutableLiveData<String>()
    val selectedImageUrl : LiveData<String>
    get() = selectedImages

    //ArtDetailsFragment

    private var insertArtMessage = MutableLiveData<Resource<Art>>()
    val insertArtMsg : LiveData<Resource<Art>>
    get() = insertArtMessage

    fun resetInsertArtMessage(){
        insertArtMessage = MutableLiveData<Resource<Art>>()
    }

    fun setSelectedImage(url : String){
        selectedImages.postValue(url)
    }

    fun deleteArt(art : Art) = viewModelScope.launch{
        repository.deleteArt(art)
    }

    fun insertArt(art : Art) = viewModelScope.launch{
        repository.insertArt(art)
    }

    fun makeArt(name : String,artistName : String,year: String){
        if(name.isEmpty() || artistName.isEmpty() || year.isEmpty()){
            insertArtMessage.postValue(Resource.error("Enter name,year,artist",null))
            return
        }

        val yearInt = try{
            year.toInt()
        }
        catch (e: Exception){
            insertArtMessage.postValue(Resource.error("Year should be number",null))
            return
        }

       val art = Art(name,artistName,yearInt,selectedImages.value ?: "")
        insertArt(art)
        setSelectedImage("")
        insertArtMessage.postValue(Resource.success(art))
    }

    fun searchForImage(searchString : String){
        if(searchString.isEmpty()){
            return
        }
        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response  = repository.searchImage(searchString)
            images.value = response
        }
    }


}