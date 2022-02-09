package com.example.tgplayer

import androidx.lifecycle.ViewModel
import com.example.tgplayer.repository.YoutubeDownloaderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DownloaderActivityViewModel @Inject constructor(
    private val youtubeDownloaderRepository: YoutubeDownloaderRepository,
) : ViewModel() {

    fun startAudioDownload(videoUrl: String) {
        youtubeDownloaderRepository.downloadAndSaveAudio(videoUrl)
    }
}