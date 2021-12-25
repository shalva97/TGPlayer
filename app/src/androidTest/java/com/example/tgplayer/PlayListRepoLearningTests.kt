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
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
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

    @Test
    fun savePlayList() {

    }

    companion object {
        val audios = listOf(
            Audio(
                name = "Doom Soundtrack Metal Cover",
                url = "asdfUrl",
                pathOnDevice = "some path",
                audioID = 0,
                thumbnailPath = "somePath",
                length = 22
            ),
            Audio(
                name = "The knife edge",
                url = "asdfUrl",
                pathOnDevice = "some path",
                audioID = 0,
                thumbnailPath = "somePath",
                length = 22
            ),
            Audio(name = "always give you up",
                url = "asdfUrl",
                pathOnDevice = "some path",
                audioID = 0,
                thumbnailPath = "somePath",
                length = 22),
            Audio(
                name = "November Rain",
                url = "asdfUrl",
                pathOnDevice = "some path",
                audioID = 0,
                thumbnailPath = "somePath",
                length = 22
            ),
        )
    }
}