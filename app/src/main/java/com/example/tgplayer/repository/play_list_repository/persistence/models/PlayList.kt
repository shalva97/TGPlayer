package com.example.tgplayer.repository.play_list_repository.persistence.models


import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.tgplayer.model.PlayList

data class PlayList(
    @Embedded
    val playlist: PlayListData,
    @Relation(
        parentColumn = "playListID",
        entityColumn = "audioID",
        associateBy = Junction(PlayListAudioCrossRef::class)
    )
    val list: List<Audio> = emptyList(),
) {
    fun toPresentationModel(): PlayList {
        return PlayList(
            name = playlist.playListID,
            color = playlist.color,
            musicList = list.map { audio ->
                audio.toPresentationModel()
            }
        )
    }
}

