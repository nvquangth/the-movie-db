package com.example.cleanarchitecture.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.data.database.room.dao.MovieDao
import com.example.cleanarchitecture.data.database.room.entity.MovieRoomEntity

@Database(entities = [MovieRoomEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}
