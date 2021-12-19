package com.example.tgplayer.model

data class PlayList(
    val name: String,
    val color: Int,
    val musicList: List<Audio>,
    var selected: Boolean = false
    )