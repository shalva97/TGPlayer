package com.example.tgplayer.di

import android.content.Context
import androidx.room.Room
import com.example.tgplayer.repository.play_list_repository.persistence.PlayListDao
import com.example.tgplayer.repository.play_list_repository.persistence.PlayListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    @Singleton
    fun room(@ApplicationContext context: Context): PlayListDao {
        return Room.databaseBuilder(context, PlayListDatabase::class.java, "playlist.db").build()
            .playlistDao()
    }

}