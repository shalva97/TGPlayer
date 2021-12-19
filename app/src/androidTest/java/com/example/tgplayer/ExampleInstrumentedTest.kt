package com.example.tgplayer

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tgplayer.repository.YoutubeDownloaderRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ExampleInstrumentedTest {

    @get:Rule
    var rule = HiltAndroidRule(this)

    @Inject
    lateinit var youtubeDownloaderRepository: YoutubeDownloaderRepository

    @Before
    fun init() {
        rule.inject()
    }

    @Test
    fun learningAboutNewPipeExtractor() {
        val video =
            youtubeDownloaderRepository.getAudio("https://www.youtube.com/watch?v=Vsn68BKNKCc")
        video.fetchPage()

        video
    }
}