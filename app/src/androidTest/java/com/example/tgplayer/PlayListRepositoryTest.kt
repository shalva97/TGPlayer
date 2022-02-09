package com.example.tgplayer

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.example.tgplayer.model.Audio
import com.example.tgplayer.model.PlayList
import com.example.tgplayer.repository.play_list_repository.PlayListRepository
import com.example.tgplayer.repository.play_list_repository.persistence.PlayListDatabase
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PlayListRepositoryTest {

    private lateinit var playListRepository: PlayListRepository
    private val faker = Faker()
    private val fakeAudios = List(5) { faker.randomProvider.randomClassInstance<Audio>() }
    private val fakePlayList = faker.randomProvider.randomClassInstance<PlayList> {
        typeGenerator { fakeAudios }
    }

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db: PlayListDatabase =
            Room.inMemoryDatabaseBuilder(context, PlayListDatabase::class.java).build()
        playListRepository = PlayListRepository(db.playlistDao())
    }

    @Test
    fun saveAndRetrievePlayList() = runBlocking {
        playListRepository.getPlaylists().test {
            playListRepository.save(fakePlayList)
            val result = awaitItem()
            assert(result.isNotEmpty())
            cancelAndConsumeRemainingEvents()
        }
    }
}