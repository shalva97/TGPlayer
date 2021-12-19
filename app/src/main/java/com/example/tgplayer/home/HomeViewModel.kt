package com.example.tgplayer.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tgplayer.R
import com.example.tgplayer.model.Audio
import com.example.tgplayer.model.PlayList
import com.example.tgplayer.repository.play_list_repository.PlayListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
   private val playerRepository: PlayListRepository
) : ViewModel() {



    init {
        getAllDataFromRoom()

    }

    private var counter = 0

    private val allPlayList = mutableListOf<PlayList>()

    private fun getAllDataFromRoom(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){

                playerRepository.getPlaylists().map {
                    allPlayList.add(
                        PlayList(
                            name = it.playlist.playListID,
                            color = it.playlist.color,
                            musicList = it.list.map { audio->
                                Audio(
                                    audioSource = audio.pathOnDevice,
                                    name = audio.name,
                                    thumbnail = audio.thumbnailPath,
                                    length = audio.length
                                )
                            }
                    )
                    )
                }
                _playList.postValue(allPlayList)


            }
        }
    }


    private var _playListName = MutableLiveData<PlayList>()
    val playListName: LiveData<PlayList> = _playListName

    private var _playList = MutableLiveData<List<PlayList>>()
    val playList: LiveData<List<PlayList>> = _playList

    private var _musicList = MutableLiveData<List<Audio>>()
     val musicList: LiveData<List<Audio>> = _musicList

    private var _showSearchIcon = MutableLiveData<Boolean>(false)
    val showSearchIcon : LiveData<Boolean> = _showSearchIcon

    fun dataManipulation(position: Int? = null) {

        if (allPlayList.size > 0){
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

        for (item in allPlayList){
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




}