package com.example.tgplayer.repository

import android.content.Context
import com.example.tgplayer.model.Audio
import com.example.tgplayer.repository.play_list_repository.persistence.models.AudioDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.schabi.newpipe.extractor.services.youtube.YoutubeService
import org.schabi.newpipe.extractor.stream.StreamExtractor
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YoutubeDownloaderRepository @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val youtubeService: YoutubeService,
    private val okHttpClient: OkHttpClient
) {

    @Throws(IOException::class)
    private fun downloadAudio(uri: String): ByteArray? {
        lateinit var response: Response
        val request = Request.Builder()
            .url(uri)
            .build()
        response = okHttpClient.newCall(request).execute()
        return response.body?.bytes()

    }

    private fun saveAudio(filePath: String, byteArray: ByteArray?) {
        context.openFileOutput(filePath, Context.MODE_PRIVATE).use { output ->
            output.write(byteArray)
        }

    }


    fun saveFileEndReturnAudioWithFileManagerPath(audio: Audio): AudioDTO {
        val byteArray = downloadAudio(audio.audioSource)
        saveAudio(audio.name, byteArray)
        return AudioDTO(
            audioID = audio.name,
            thumbnailPath = audio.thumbnail,
            length = audio.length,
            audioSource = audio.audioSource
        )
    }

    fun getAudioFromFile(filePath: String){
        context.openFileInput(filePath).use { stream ->
            val audio = stream.bufferedReader().use {
                it.readText()
            }
        }
    }


    fun getAudioForTest(url: String): StreamExtractor {
        val video = youtubeService.getStreamExtractor(url)

        video.fetchPage()

        return video
    }

    fun getAudio(url: String): Audio {
        val video = youtubeService.getStreamExtractor(url)
        video.fetchPage()

        return Audio(
            audioSource = video.audioStreams.sortedBy { it.bitrate }.last().url,
            name = video.name+ ".mp3",
            thumbnail = video.thumbnailUrl,
            length = video.length,

        )

    }


}