package com.example.tgplayer

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tgplayer.repository.play_list_repository.persistence.PlayListDao
import com.example.tgplayer.repository.play_list_repository.persistence.PlayListDatabase
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class PlayListRepoLearningTests {

    private lateinit var playListDao: PlayListDao
    private lateinit var db: PlayListDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, PlayListDatabase::class.java).build()
        playListDao = db.playlistDao()
    }

    @Test()
    fun doStuff() {

    }
}