package com.example.tgplayer.repository

import org.schabi.newpipe.extractor.services.youtube.YoutubeService
import org.schabi.newpipe.extractor.stream.StreamExtractor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YoutubeDownloaderRepository @Inject constructor(
    private val youtubeService: YoutubeService,
) {

    init {
        123
    }

    fun getVideo(url: String): StreamExtractor {
        return youtubeService.getStreamExtractor(url)
    }
}