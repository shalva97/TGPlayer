package com.example.tgplayer.repository.play_list_repository.persistence.models

import androidx.room.Entity

@Entity(primaryKeys = ["playListID", "audioID"])
data class PlayListAudioCrossRef(
    val playListID: String,
    val audioID: String,
)