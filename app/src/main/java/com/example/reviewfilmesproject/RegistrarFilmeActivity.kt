package com.example.reviewfilmesproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.example.reviewfilmesproject.Model.Filme
import com.example.reviewfilmesproject.Service.FilmeService
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class RegistrarFilmeActivity : AppCompatActivity() {

    companion object{
        const val RESULT_CODE_REGISTER = 1
    }

    lateinit var edtTitle: EditText
    lateinit var edtDescription: EditText
    lateinit var edtURLImage: EditText
    lateinit var edtNota: EditText
    lateinit var btnRegister: Button
    lateinit var btnEditar: Button
    var filme : Filme? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_filme)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Registrar Filme"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        edtTitle = findViewById(R.id.edtTitle)
        edtDescription = findViewById(R.id.edtDescription)
        edtURLImage = findViewById(R.id.edtImage)
        edtNota = findViewById(R.id.edtNota)
        btnRegister = findViewById(R.id.btnRegistrar)

        btnRegister.setOnClickListener {
            var filme = Filme()
            filme.urlImagem = edtURLImage.text.toString()
            filme.titulo = edtTitle.text.toString()
            filme.sinopse = edtDescription.text.toString()
            filme.nota = edtNota.text.toString().toDouble()
            inserir(filme)

            var i = Intent()
            i.putExtra("filme", filme)
            setResult(RESULT_CODE_REGISTER, i)
            finish()
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    open fun inserir(filme: Filme){

        doAsync {
            Log.e("DAO-Filme", "Inserir doAsync")
            FilmeService.inserir(filme)
            uiThread {
                Log.e("DAO-Filme", "Inserir uiThread")
            }
        }

    }
}
