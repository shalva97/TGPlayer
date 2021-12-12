package com.example.tgplayer

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.ServiceList

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun learningAboutNewPipeExtractor() {
        NewPipe.init(DownloaderImpl.init(null))
        val streamThing =
            ServiceList.YouTube.getStreamExtractor("https://www.youtube.com/watch?v=Vsn68BKNKCc")
        streamThing.fetchPage()
        
        streamThing
    }
}