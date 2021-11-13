package com.example.project1.Util

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project1.Model.RoomMemo

@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
abstract class RoomHelper: RoomDatabase() {
    abstract fun roomMemoDao() : RoomMemoDao
}