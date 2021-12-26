package com.example.tgplayer.repository.play_list_repository.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tgplayer.repository.play_list_repository.persistence.models.AudioDTO
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayList
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayListAudioCrossRef
import com.example.tgplayer.repository.play_list_repository.persistence.models.PlayListDTO

@Dao
interface PlayListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPlayList(list: List<PlayListDTO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAudio(vararg list: AudioDTO)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPlayList(playList: PlayListDTO)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAudioToPlayList(vararg ref: PlayListAudioCrossRef)


    @Query("SELECT * FROM PlayListDTO")
    fun getPlayLists(): List<PlayList>

    @Query("SELECT * FROM AudioDTO")
    fun getAllAudio(): List<AudioDTO>

//    @Insert
//    fun addRelation()
//    fun removePlaylist()
//    fun getPlaylist()
//    fun update()
}
