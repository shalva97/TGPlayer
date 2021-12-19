package com.example.tgplayer.repository.play_list_repository.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tgplayer.repository.play_list_repository.persistence.models.Audio
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayListAudioCrossRef
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayListData

@Database(entities = [PlayListData::class, Audio::class, PlayListAudioCrossRef::class], version = 1)
abstract class PlayListDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlayListDao
}