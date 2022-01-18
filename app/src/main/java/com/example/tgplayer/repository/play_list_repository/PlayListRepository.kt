package com.example.tgplayer.repository.play_list_repository

import com.example.tgplayer.model.Audio
import com.example.tgplayer.model.PlayList
import com.example.tgplayer.repository.play_list_repository.persistence.PlayListDao
import com.example.tgplayer.repository.play_list_repository.persistence.models.AudioDTO
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayListAudioCrossRef
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayListDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlayListRepository @Inject constructor(
    private val playListDao: PlayListDao,
) {

    fun getPlaylists(): Flow<List<PlayList>> {
        return playListDao.getPlayLists().map { playlists ->
            playlists.map { playList -> playList.toPresentationModel() }
        }
    }

    fun getAllAudio(): Flow<List<Audio>> {
        return playListDao.getAllAudio().map { audio ->
            audio.map { it.toPresentationModel() }
        }
    }

    fun addAudio(audio: AudioDTO) {
        playListDao.addAudio(audio)
    }

    fun save(playList: PlayList) {

        playListDao.addPlayList(playList.toRepositoryModel())
        playListDao.addAudio(*(playList.musicList.map { it.toRepositoryModel() }.toTypedArray()))

        val relationships =
            playList.musicList.map { PlayListAudioCrossRef(playList.name, it.name) }

        playListDao.addAudioToPlayList(*relationships.toTypedArray())
    }

    private fun PlayList.toRepositoryModel(): PlayListDTO {
        return PlayListDTO(
            playListID = name,
            color = color
        )
    }

    private fun Audio.toRepositoryModel(): AudioDTO {
        return AudioDTO(
            audioID = name,
            thumbnailPath = thumbnail,
            length = length,
            audioSource = audioSource
        )
    }
}

