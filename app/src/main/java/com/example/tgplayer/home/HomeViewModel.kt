package com.example.tgplayer.home

import android.content.Intent
import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tgplayer.R
import com.example.tgplayer.model.Audio
import com.example.tgplayer.model.PlayList
import android.content.pm.ResolveInfo

import android.content.pm.PackageManager

import android.content.ComponentName




class HomeViewModel : ViewModel() {

    private var counter = 0
    private val fakeMusicListGenerator = listOf(
        Audio(
            audioSource = "",
            name = "Shatilis asulo",
            thumbnail = "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80",
            length = 1.320f
        ),
        Audio(
            audioSource = "",
            name = "Shatilis asulo",
            thumbnail = "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80",
            length = 1.320f
        ),
        Audio(
            audioSource = "",
            name = "Shatilis asulo",
            thumbnail = "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80",
            length = 1.320f
        ),
        Audio(
            audioSource = "",
            name = "Shatilis asulo",
            thumbnail = "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80",
            length = 1.320f
        )
    )

    private val fakeData = listOf(
        PlayList(name = "როკი", color = R.color.Material_deep_purple, musicList = fakeMusicListGenerator),
        PlayList(name = "პოპი", color = R.color.Material_indigo, musicList = fakeMusicListGenerator),
        PlayList(name = "ჯაზი", color = R.color.Material_red, musicList = fakeMusicListGenerator),
    )

    private var _playListName = MutableLiveData<PlayList>()
    val playListName: LiveData<PlayList> = _playListName

    private var _playList = MutableLiveData<List<PlayList>>()

     val playList: LiveData<List<PlayList>> = _playList

    private var _musicList = MutableLiveData<List<Audio>>()
     val musicList: LiveData<List<Audio>> = _musicList

    private var _showSearchIcon = MutableLiveData<Boolean>(false)
    val showSearchIcon : LiveData<Boolean> = _showSearchIcon

    fun getFakeData(position: Int? = null) {

        if (counter == 0) {
            fakeData[0].selected = true
            counter++
            _musicList.postValue(fakeData[0].musicList)
            _playListName.postValue(fakeData[0])
        }

        _playList.postValue(fakeData)
        position?.let {
            _musicList.postValue(fakeData[it].musicList)
            _playListName.postValue(fakeData[it])
        }


    }

    fun itemClicked(playListPosition: Int) {

        for (item in fakeData){
            item.selected = false
        }
        fakeData[playListPosition].selected = true
        getFakeData(playListPosition)

    }

    fun search(Text: String) {
        _showSearchIcon.postValue(true)
    }

    fun finishSearch() {

        _showSearchIcon.postValue(false)
    }




}