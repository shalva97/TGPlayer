package com.example.tgplayer.repository.play_list_repository.persistence.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Audio(
    val name: String,
    val url: String,
    val pathOnDevice: String,
    @PrimaryKey(autoGenerate = true)
    val audioID: Int = 0,
    val thumbnailPath: String,
    val length: Float
) {

}