package com.example.reviewfilmesproject.Database

import androidx.room.Room
import com.example.reviewfilmesproject.DAO.FilmeDAO
import com.example.reviewfilmesproject.FilmeApplication

object DatabaseManager {

    private var appDatabase : Database

    init {
        val appContext = FilmeApplication.getInstance().applicationContext
        appDatabase = Room.databaseBuilder(
            appContext,
            Database::class.java,
            "dbReview.sqlite"
        ).allowMainThreadQueries().build()
    }

    fun FilmeDAO(): FilmeDAO {
        return appDatabase.filmeDAO()
    }
}