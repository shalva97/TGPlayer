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

    val playListName = MutableLiveData<PlayList>()
    val playList = MutableLiveData<List<PlayList>>()
    val musicList = MutableLiveData<List<Audio>>()
    val showSearchIcon = MutableLiveData(false)
    private var counter = 0
    private var allPlayList = mutableListOf<PlayList>()

    init {
        getAllDataFromRoom()
    }

    private fun getAllDataFromRoom() = viewModelScope.launch(Dispatchers.IO) {
        // TODO
        val allAudio = playerRepository.getAllAudio().first()
        musicList.postValue(allAudio)
        playList.postValue(allAudioPlayLists)
    }

    fun dataManipulation(position: Int? = null) {
        if (allPlayList.size > 0) {
            if (counter == 0) {
                allPlayList[0].selected = true
                counter++
                musicList.postValue(allPlayList[0].musicList)
                playListName.postValue(allPlayList[0])
            }
            playList.postValue(allPlayList)
            position?.let {
                musicList.postValue(allPlayList[it].musicList)
                playListName.postValue(allPlayList[it])
            }
        }
    }

    fun itemClicked(playListPosition: Int) {
        for (item in allPlayList) {
            item.selected = false
        }
        allPlayList[playListPosition].selected = true
        dataManipulation(playListPosition)

    }

    fun search(Text: String) {
        showSearchIcon.postValue(true)
    }

    fun finishSearch() {
        showSearchIcon.postValue(false)
    }

    companion object {
        val allAudioPlayLists = listOf<PlayList>(
            PlayList(
                name = "All",
                color = R.color.purple_200,
                musicList = emptyList(), selected = true
            ),
            PlayList(
                name = "Favorite",
                color = R.color.purple_700,
                musicList = emptyList(), selected = false
            ),
            PlayList(
                name = "Shalioza",
                color = R.color.teal_200,
                musicList = emptyList(), selected = false
            )
        )
    }
}