package com.example.cleanarchitecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DeveloperTable::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun developerDao(): DeveloperDao
}