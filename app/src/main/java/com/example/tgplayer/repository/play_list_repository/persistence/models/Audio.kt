package com.example.tgplayer.repository.play_list_repository.persistence.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tgplayer.model.Audio

@Entity
data class Audio(
    val name: String,
    val url: String,
    val pathOnDevice: String,
    @PrimaryKey(autoGenerate = true)
    val audioID: Int = 0,
    val thumbnailPath: String,
    val length: Long
) {
    fun toPresentationModel(): Audio {
        return Audio(
            audioSource = pathOnDevice,
            name = name,
            thumbnail = thumbnailPath,
            length = length
        )
    }
}