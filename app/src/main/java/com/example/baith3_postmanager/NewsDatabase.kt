package com.example.baith3_postmanager

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class], version = 2)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun doa():NewsDao
}