package com.example.tgplayer.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tgplayer.repository.YoutubeDownloaderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val youtubeDownloaderRepository: YoutubeDownloaderRepository,
) : ViewModel() {



    init {

    }




}