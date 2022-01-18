package com.example.tgplayer.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tgplayer.R
import com.example.tgplayer.model.Audio
import com.example.tgplayer.model.PlayList
import com.example.tgplayer.repository.IntentEvents
import com.example.tgplayer.repository.YoutubeDownloaderRepository
import com.example.tgplayer.repository.play_list_repository.PlayListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val playerRepository: PlayListRepository,
    private val youtubeDownloaderRepository: YoutubeDownloaderRepository,
    events: IntentEvents,
) : ViewModel() {

    private var _playListName = MutableLiveData<PlayList>()
    val playListName: LiveData<PlayList> = _playListName

    private var _playList = MutableLiveData<List<PlayList>>()
    val playList: LiveData<List<PlayList>> = _playList

    private var _musicList = MutableLiveData<List<Audio>>()
    val musicList: LiveData<List<Audio>> = _musicList

    private var _showSearchIcon = MutableLiveData<Boolean>(false)
    val showSearchIcon: LiveData<Boolean> = _showSearchIcon

    private var counter = 0
    private var allPlayList = mutableListOf<PlayList>()

    private var _audioBeforeDownload = MutableLiveData<Audio>()
    val audioBeforeDownload: LiveData<Audio> = _audioBeforeDownload

    val audios = playerRepository.getAllAudio()

    init {
        getAllDataFromRoom()
        events.youtubeLinkIntent.observeForever {
            getAudioObjectBeforeDownload(it)

        }
    }

    fun getAudioObjectBeforeDownload(link: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val data = youtubeDownloaderRepository.getAudio(link)
                _audioBeforeDownload.postValue(data)
            }
        }
    }

    private fun getAllDataFromRoom() = viewModelScope.launch(Dispatchers.IO) {
        val playlists = mutableListOf<PlayList>()
//        playlists.add(allAudioPlaylist.copy(musicList = audios.value ?: emptyList()))
//        playlists.addAll(playerRepository.getPlaylists()?.value ?: emptyList())
//TODO
        _playList.postValue(allPlayList)
    }


    fun dataManipulation(position: Int? = null) {

        if (allPlayList.size > 0) {
            if (counter == 0) {
                allPlayList[0].selected = true
                counter++
                _musicList.postValue(allPlayList[0].musicList)
                _playListName.postValue(allPlayList[0])
            }

            _playList.postValue(allPlayList)
            position?.let {
                _musicList.postValue(allPlayList[it].musicList)
                _playListName.postValue(allPlayList[it])
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
        _showSearchIcon.postValue(true)
    }

    fun finishSearch() {

        _showSearchIcon.postValue(false)
    }

    fun downloadAudio(it: Audio) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val roomdata =
                    youtubeDownloaderRepository.saveFileEndReturnAudioWithFileManagerPath(it)
                playerRepository.addAudio(roomdata)
            }
        }

    }

    companion object {
        val allAudioPlaylist = PlayList("All", R.color.purple_200, emptyList(), selected = true)
    }
}