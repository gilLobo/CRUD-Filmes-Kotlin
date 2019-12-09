package com.example.reviewfilmesproject.DAO

import androidx.room.*
import com.example.reviewfilmesproject.Model.Filme

@Dao
interface FilmeDAO {

    @Query("SELECT * FROM filme WHERE id = :id")
    fun findById(id:Long): Filme
    @Query("SELECT * FROM filme")
    fun findAll(): List<Filme>
    @Insert
    fun insert(filme: Filme)
    @Update
    fun update(filme: Filme)
    @Delete
    fun delete(filme: Filme)

}