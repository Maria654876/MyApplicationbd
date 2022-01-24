package com.example.myapplication.bd

import androidx.room.*

interface CachingCountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCount(count: CachingCount)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCounts(counts: List<CachingCount>)

    @Delete
    fun deleteCount(count: CachingCount)

    @Delete
    fun deleteCounts(vararg count: CachingCount)

    @Query("DELETE FROM cachingCount")
    fun deleteCounts()

    @Update
    fun updateCount(count: CachingCount)

    @Query("SELECT * FROM cachingCount")
    fun getCounts(): List<CachingCount>?

    @Query("SELECT * FROM cachingCount WHERE id LIKE :id")
    fun getCountById(id: Int): CachingCount?
}