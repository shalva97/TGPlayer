package com.example.tgplayer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Audio(
    val audioSource: String,
    val name: String,
    val thumbnail: String,
    val length: Long
):Parcelable