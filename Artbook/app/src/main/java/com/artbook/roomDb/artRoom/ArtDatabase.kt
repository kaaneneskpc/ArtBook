package com.artbook.roomDb.artRoom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artbook.model.Art


@Database(entities = [Art::class], version = 1)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao(): ArtDao
}