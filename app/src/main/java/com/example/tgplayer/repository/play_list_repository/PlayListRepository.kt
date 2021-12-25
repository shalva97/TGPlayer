package com.example.tgplayer.repository.play_list_repository

import com.example.tgplayer.model.Audio
import com.example.tgplayer.model.PlayList
import com.example.tgplayer.repository.RoomAudio
import com.example.tgplayer.repository.play_list_repository.persistence.PlayListDao
import javax.inject.Inject

class PlayListRepository @Inject constructor(
    private val playListDao: PlayListDao,
) {

    fun getPlaylists(): List<PlayList> {
        return playListDao.getPlayLists().map { it.toPresentationModel() }
    }

    fun getAllAudio(): List<Audio> {
        return playListDao.getAllAudio()
            .map { audio -> audio.toPresentationModel() }
    }

    fun addAudio(audio: RoomAudio){
        playListDao.addAudio(audio)
    }

    fun save(playList: PlayList) {

    }
}

