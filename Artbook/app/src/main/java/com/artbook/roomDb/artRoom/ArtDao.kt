package com.artbook.roomDb.artRoom

import androidx.lifecycle.LiveData
import androidx.room.*
import com.artbook.model.Art

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art : Art)

    @Delete
    suspend fun deleteArt(art: Art)

    @Query("SELECT * FROM arts")
    fun observeArt():LiveData<List<Art>>

}