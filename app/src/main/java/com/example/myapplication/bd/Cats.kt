package com.example.myapplication.bd

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Cats(
    @PrimaryKey
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String
)
