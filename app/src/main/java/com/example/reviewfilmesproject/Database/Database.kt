package com.example.reviewfilmesproject.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reviewfilmesproject.DAO.FilmeDAO
import com.example.reviewfilmesproject.Model.Filme


@Database(entities = arrayOf(Filme::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun filmeDAO(): FilmeDAO
}