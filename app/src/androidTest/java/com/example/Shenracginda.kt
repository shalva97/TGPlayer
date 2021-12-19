package com.example

import com.example.tgplayer.R
import com.example.tgplayer.model.Audio
import com.example.tgplayer.model.PlayList

private val fakeMusicListGenerator = listOf(
    Audio(
        audioSource = "",
        name = "Shatilis asulo",
        thumbnail = "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80",
        length = 1.320f
    ),
    Audio(
        audioSource = "",
        name = "Shatilis asulo",
        thumbnail = "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80",
        length = 1.320f
    ),
    Audio(
        audioSource = "",
        name = "Shatilis asulo",
        thumbnail = "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80",
        length = 1.320f
    ),
    Audio(
        audioSource = "",
        name = "Shatilis asulo",
        thumbnail = "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80",
        length = 1.320f
    )
)

private val fakeData = listOf(
    PlayList(name = "როკი", color = R.color.Material_deep_purple, musicList = fakeMusicListGenerator),
    PlayList(name = "პოპი", color = R.color.Material_indigo, musicList = fakeMusicListGenerator),
    PlayList(name = "ჯაზი", color = R.color.Material_red, musicList = fakeMusicListGenerator),
)