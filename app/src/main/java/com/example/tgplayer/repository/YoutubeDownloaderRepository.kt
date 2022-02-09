package com.example.tgplayer.repository

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.net.toUri
import com.example.tgplayer.model.Audio
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.schabi.newpipe.extractor.services.youtube.YoutubeService
import org.schabi.newpipe.extractor.stream.StreamExtractor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YoutubeDownloaderRepository @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val youtubeService: YoutubeService,
) {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun getAudioForTest(url: String): StreamExtractor {
        val video = youtubeService.getStreamExtractor(url)
        video.fetchPage()
        return video
    }

    fun downloadAndSaveAudio(url: String) = ioScope.launch {
        val audio = getAudioInfo(url)
        downloadAudioViaDownloadManager(audio)
    }

    private fun getAudioInfo(url: String): Audio {
        val video = youtubeService.getStreamExtractor(url)
        video.fetchPage()

        return Audio(
            audioSource = video.audioStreams.maxByOrNull { it.bitrate }!!.url,
            name = video.name + ".mp3",
            thumbnail = video.thumbnailUrl,
            length = video.length
        )
    }

    private fun downloadAudioViaDownloadManager(audioInfo: Audio) {
        val downloadRequest = DownloadManager.Request(audioInfo.audioSource.toUri())
            .setTitle(audioInfo.name)
            .setDescription("Downloading...")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, audioInfo.name)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val downloadManager = getSystemService(context, DownloadManager::class.java)

        downloadManager?.enqueue(downloadRequest)
    }
}