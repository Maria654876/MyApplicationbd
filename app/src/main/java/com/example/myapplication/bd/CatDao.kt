package com.example.myapplication.bd

import androidx.room.*
import com.example.myapplication.cats.Cat

interface CatDao {
    @Insert
    fun insertCat(cat: Cats)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCats(cat: List<Cat>)

    @Delete
    fun deleteCat(cat: Cat)

    @Delete
    fun deleteCats(vararg cat: Cat)

    @Query("DELETE FROM Cat")
    fun deleteCats()

    @Update
    fun updateCat(cat: Cat)

    @Query("SELECT * FROM Cat")
    fun getCats(): List<Cat>

    @Query("""SELECT * FROM Cat WHERE name LIKE :catName""")
    fun searchCats(catName: String): List<Cat>
}