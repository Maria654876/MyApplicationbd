package com.example.myapplication.bd

import android.content.Context

class AppDatabaseImp private constructor() : CatDatabase, CachingCountDataBase{

    companion object {
        private var instance: AppDatabaseImp? = null
        private var catDao: CatDao? = null
        private var cachingCountDao: CachingCountDao? = null

        fun getInstance(context: Context): AppDatabaseImp {
            if (instance == null) {
                instance = AppDatabaseImp()
                catDao = AppDatabase.buildDatabase(context).getCatDao()
                cachingCountDao = AppDatabase.buildDatabase(context).getCachingCountDao()
            }

            return instance!!
        }
    }


    override fun getCachingCountDao(): CachingCountDao = cachingCountDao!!

    override fun getCat(): CatDao =catDao!!
}