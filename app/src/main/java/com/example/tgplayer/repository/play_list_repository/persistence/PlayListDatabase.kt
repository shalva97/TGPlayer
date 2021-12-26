package com.example.tgplayer.repository.play_list_repository.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tgplayer.repository.play_list_repository.persistence.models.AudioDTO
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayListAudioCrossRef
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayListDTO

@Database(entities = [PlayListDTO::class, AudioDTO::class, PlayListAudioCrossRef::class],
    version = 1)
abstract class PlayListDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlayListDao
}