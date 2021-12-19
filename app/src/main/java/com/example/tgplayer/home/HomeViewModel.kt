package com.example.tgplayer.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tgplayer.repository.play_list_repository.PlayListRepository
import com.example.tgplayer.repository.play_list_repository.persistence.models.Audio
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val playerListRepository: PlayListRepository
) : ViewModel() {

    private var counter = 0

    private var allPlayLists: List<PlayList> = emptyList()


    private var _playListName = MutableLiveData<PlayList>()
    val playListName: LiveData<PlayList> = _playListName


    private var _playList = MutableLiveData<List<PlayList>>()
    val playList: LiveData<List<PlayList>> = _playList

    private var _musicList = MutableLiveData<List<Audio>>()
    val musicList: LiveData<List<Audio>> = _musicList

    private var _showSearchIcon = MutableLiveData<Boolean>(false)
    val showSearchIcon : LiveData<Boolean> = _showSearchIcon

    init {
        getAllData()
    }


    fun getAllData(){
        viewModelScope.launch {
           withContext(Dispatchers.IO){
               allPlayLists = playerListRepository.getPlaylists()
               _playList.postValue(allPlayLists)
           }
        }
    }




    fun getFakeData(position: Int? = null) {

        if (counter == 0) {
            allPlayLists[0].playlist.selected = true
            counter++
            _musicList.postValue(allPlayLists[0].list)
            _playListName.postValue(allPlayLists[0])
        }

        _playList.postValue(allPlayLists)
        position?.let {
            _musicList.postValue(allPlayLists[it].list)
            _playListName.postValue(allPlayLists[it])
        }


    }

    fun itemClicked(playListPosition: Int) {

        for (item in allPlayLists){
            item.playlist.selected = false
        }
        allPlayLists[playListPosition].playlist.selected = true
        getFakeData(playListPosition)

    }

    fun search(Text: String) {
        _showSearchIcon.postValue(true)
    }

    fun finishSearch() {

        _showSearchIcon.postValue(false)
    }




}