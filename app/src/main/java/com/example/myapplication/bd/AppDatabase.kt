package com.example.myapplication.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cats::class, CachingCount::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "AppDatabase"
        ).build()
    }

    abstract fun getCatDao(): CatDao

    abstract fun getCachingCountDao(): CachingCountDao
}