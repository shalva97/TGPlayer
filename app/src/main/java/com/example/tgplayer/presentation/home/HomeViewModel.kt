package com.example.tgplayer.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tgplayer.R
import com.example.tgplayer.model.Audio
import com.example.tgplayer.model.PlayList
import com.example.tgplayer.repository.YoutubeDownloaderRepository
import com.example.tgplayer.repository.play_list_repository.PlayListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val playerRepository: PlayListRepository,
    private val youtubeDownloaderRepository: YoutubeDownloaderRepository,
) : ViewModel() {

    val musicList = MutableLiveData<List<Audio>>()
    val showSearchIcon = MutableLiveData(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            musicList.postValue(playerRepository.getAllAudio().first())
        }
    }

    fun search(Text: String) {
        showSearchIcon.postValue(true)
    }

    fun finishSearch() {
        showSearchIcon.postValue(false)
    }
}