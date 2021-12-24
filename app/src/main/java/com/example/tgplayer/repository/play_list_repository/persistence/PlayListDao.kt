package com.example.tgplayer.repository.play_list_repository.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.tgplayer.repository.play_list_repository.persistence.models.Audio
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayList
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayListAudioCrossRef
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayListData

@Dao
interface PlayListDao {

    @Insert
    fun addPlayList(list: List<PlayListData>)

    @Insert
    fun addAudio(list: List<Audio>)

    @Insert
    fun addAudioToPlayList(ref: PlayListAudioCrossRef)

    @Transaction
    @Query("SELECT * FROM PlayListData")
    fun getPlayLists(): List<PlayList>

    @Query("SELECT * FROM Audio")
    fun getAllAudio(): List<Audio>

//    @Insert
//    fun addRelation()
//    fun removePlaylist()
//    fun getPlaylist()
//    fun update()
}
