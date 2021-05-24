package com.artbook.repository

import androidx.lifecycle.LiveData
import com.artbook.model.Art
import com.artbook.model.ImageResponse
import com.artbook.util.Resource

interface ArtRepositoryInterface {
    suspend fun insertArt(art : Art)
    suspend fun deleteArt(art : Art)
    fun getArt() : LiveData<List<Art>>
    suspend fun searchImage(imageString : String) : Resource<ImageResponse>

}