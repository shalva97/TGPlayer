package com.example.tgplayer.presentation.player

import androidx.lifecycle.ViewModel
import com.example.tgplayer.repository.YoutubeDownloaderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val youtubeDownloaderRepository: YoutubeDownloaderRepository,
) : ViewModel() {



    init {

    }




}