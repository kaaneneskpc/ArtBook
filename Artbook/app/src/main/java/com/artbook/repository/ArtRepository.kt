package com.artbook.repository

import androidx.lifecycle.LiveData
import com.artbook.api.ArtImageAPI
import com.artbook.model.Art
import com.artbook.model.ImageResponse
import com.artbook.roomDb.artRoom.ArtDao
import com.artbook.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao : ArtDao,
    private val artImageAPI : ArtImageAPI,
): ArtRepositoryInterface {
    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return  artDao.observeArt()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try{
           val response = artImageAPI.imageSearch(imageString)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            }
            else{
                Resource.error("Error",null)
            }
        } catch(e:Exception) {
            Resource.error("No Data",null)
        }
    }
}