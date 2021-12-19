package com.example.tgplayer.repository.play_list_repository

import com.example.tgplayer.repository.play_list_repository.persistence.PlayListDao
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayList
import javax.inject.Inject

class PlayListRepository @Inject constructor(
    private val playListDao: PlayListDao,
) {

    fun getPlaylists(): List<PlayList> {
        return playListDao.getPlayLists()
    }

}

