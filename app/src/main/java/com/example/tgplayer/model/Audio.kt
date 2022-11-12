package com.example.tgplayer.model

import android.os.Parcelable
import com.example.tgplayer.repository.play_list_repository.persistence.models.AudioDTO
import kotlinx.parcelize.Parcelize


@Parcelize
data class Audio(
    val audioSource: String,
    val name: String,
    val thumbnail: String,
    val length: Long
) : Parcelable


fun Audio.toDTO(): AudioDTO {
    return AudioDTO(
        audioID = this.name,
        thumbnailPath = this.thumbnail,
        length = this.length,
        audioSource = this.audioSource
    )
}

