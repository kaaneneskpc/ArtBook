package com.artbook.dependencyInjection

import android.content.Context
import androidx.room.Room
import com.artbook.R
import com.artbook.api.ArtImageAPI
import com.artbook.repository.ArtRepository
import com.artbook.repository.ArtRepositoryInterface
import com.artbook.roomDb.artRoom.ArtDao
import com.artbook.roomDb.artRoom.ArtDatabase
import com.artbook.util.Constants.BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,ArtDatabase::class.java,"ArtBookDatabase"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database: ArtDatabase) = database.artDao()

    @Singleton
    @Provides
    fun injectArtImageAPI() : ArtImageAPI{
        return  Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(ArtImageAPI::class.java)
    }
    @Singleton
    @Provides

    fun injectRepo(dao : ArtDao,apiImage : ArtImageAPI) = ArtRepository(dao,apiImage) as ArtRepositoryInterface

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context : Context) = Glide.with(context)
        .setDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background))


}