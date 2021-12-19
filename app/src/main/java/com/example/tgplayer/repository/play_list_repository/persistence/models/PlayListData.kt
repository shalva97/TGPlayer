package com.example.tgplayer.repository.play_list_repository.persistence.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class PlayListData(
    @PrimaryKey
    val playListID: String,
    val color: Int,
    @Ignore
    var selected: Boolean,
)