package com.example.cleanarchitecture.data.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchitecture.data.database.room.entity.MovieRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<MovieRoomEntity>)

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<MovieRoomEntity>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovie(id: Int): Flow<MovieRoomEntity>
}
