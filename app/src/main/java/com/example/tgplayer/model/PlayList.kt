package com.example.tgplayer.model


data class PlayList(var name: String, var color: Int, val musicList: List<Audio>, var selected: Boolean = false) {
}