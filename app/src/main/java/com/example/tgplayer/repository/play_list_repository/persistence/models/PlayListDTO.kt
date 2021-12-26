package com.example.tgplayer.repository.play_list_repository.persistence.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayListDTO(
    @PrimaryKey
    val playListID: String,
    val color: Int,
)