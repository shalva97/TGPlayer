package com.example.tgplayer.repository.play_list_repository.persistence.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tgplayer.model.Audio

@Entity
data class AudioDTO(
    @PrimaryKey val audioID: String,
    val thumbnailPath: String,
    val length: Long,
    val audioSource: String,
) {
    fun toPresentationModel(): Audio {
        return Audio(
            audioSource = audioSource,
            name = audioID,
            thumbnail = thumbnailPath,
            length = length,
        )
    }
}