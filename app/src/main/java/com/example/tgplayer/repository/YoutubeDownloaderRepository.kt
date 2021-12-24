package com.example.tgplayer.repository

import android.net.Uri
import com.example.tgplayer.model.Audio
import org.schabi.newpipe.extractor.services.youtube.YoutubeService
import org.schabi.newpipe.extractor.stream.AudioStream
import org.schabi.newpipe.extractor.stream.StreamExtractor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YoutubeDownloaderRepository @Inject constructor(
    private val youtubeService: YoutubeService,
) {



     fun getAudioForTest(url: String): StreamExtractor {
        val video = youtubeService.getStreamExtractor(url)

         video.fetchPage()





         return video
    }

    fun getAudio(url: String):Audio{
        val video = youtubeService.getStreamExtractor(url)
        video.fetchPage()

        return  Audio(
            audioSource = video.audioStreams.sortedBy { it.bitrate }.last().url,
            name = video.name,
            thumbnail = video.thumbnailUrl,
            length = video.length
        )

    }


}