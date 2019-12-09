package com.example.reviewfilmesproject.Service

import com.example.reviewfilmesproject.Database.DatabaseManager
import com.example.reviewfilmesproject.Model.Filme

object FilmeService {

    fun inserir(filme: Filme) = DatabaseManager.FilmeDAO().insert(filme)
    fun alterar(filme: Filme) = DatabaseManager.FilmeDAO().update(filme)
    fun deletar(filme: Filme) = DatabaseManager.FilmeDAO().delete(filme)
    fun listarTodas(): List<Filme> = DatabaseManager.FilmeDAO().findAll()
    fun buscarPorId(id:Long): Filme = DatabaseManager.FilmeDAO().findById(id)
}