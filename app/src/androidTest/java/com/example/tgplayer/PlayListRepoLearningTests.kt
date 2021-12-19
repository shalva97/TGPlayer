package com.example.tgplayer

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tgplayer.repository.play_list_repository.persistence.PlayListDao
import com.example.tgplayer.repository.play_list_repository.persistence.PlayListDatabase
import com.example.tgplayer.repository.play_list_repository.persistence.models.Audio
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayListAudioCrossRef
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayListData
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@Ignore("we do not need to run learning tests")
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
        val audios = listOf(
            Audio("Doom Soundtrack Metal Cover", "asdfUrl", "some path"),
            Audio("The knife edge", "asdfUrl", "some path"),
            Audio("always give you up", "asdfUrl", "some path"),
            Audio("November Rain", "asdfUrl", "some path"),
        )
        val playlist = listOf(
            PlayListData("one", 123),
            PlayListData("two", 123),
            PlayListData("three", 123),
            PlayListData("blah", 123),
        )
        playListDao.addPlayList(
            playlist
        )

        playListDao.addAudio(audios)
        playListDao.addAudioToPlayList(PlayListAudioCrossRef("one", 1))
        playListDao.addAudioToPlayList(PlayListAudioCrossRef("one", 2))
        playListDao.addAudioToPlayList(PlayListAudioCrossRef("one", 3))

        playListDao.addAudioToPlayList(PlayListAudioCrossRef("two", 2))
        playListDao.addAudioToPlayList(PlayListAudioCrossRef("blah", 2))

        val playLists = playListDao.getPlayLists()
        123
    }

}