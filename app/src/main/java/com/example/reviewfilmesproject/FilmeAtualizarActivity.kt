package com.example.reviewfilmesproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.reviewfilmesproject.Model.Filme
import com.example.reviewfilmesproject.Service.FilmeService
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.concurrent.TimeUnit

class FilmeAtualizarActivity : AppCompatActivity() {

    companion object{
        const val RESULT_CODE_REGISTER = 1
    }

    lateinit var edtTitle: EditText
    lateinit var edtDescription: EditText
    lateinit var edtURLImage: EditText
    lateinit var edtNota: EditText
    lateinit var btnEditar: Button
    var filmeEdit : Filme? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filme_atualizar)


        var toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Registrar Filme"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        edtTitle = findViewById(R.id.edtTitle)
        edtDescription = findViewById(R.id.edtDescription)
        edtURLImage = findViewById(R.id.edtImage)
        edtNota = findViewById(R.id.edtNota)

        filmeEdit = intent.getParcelableExtra<Filme>("filme")

        if(filmeEdit != null){
            btnEditar = findViewById(R.id.btnEditar)
            edtURLImage.setText(filmeEdit!!.urlImagem)
            edtTitle.setText(filmeEdit!!.titulo)
            edtDescription.setText(filmeEdit!!.sinopse)
            edtNota.setText(filmeEdit!!.nota.toString())

            btnEditar.setOnClickListener {

                var filme = Filme()
                filme.urlImagem = edtURLImage.text.toString()
                filme.titulo = edtTitle.text.toString()
                filme.sinopse = edtDescription.text.toString()
                filme.nota = edtNota.text.toString().toDouble()
                atualizar(filme)
                var i = Intent()
                i.putExtra("filme", filme)
                setResult(RegistrarFilmeActivity.RESULT_CODE_REGISTER, i)
                finish()
            }

        }else{
            Log.e("FilmeAtualizarActivity", "Erro ao retornar filme")
        }
    }

    open fun atualizar(filme: Filme){
        Toast.makeText(FilmeDetalheActivity@this, "filme: " + filme.toString(), Toast.LENGTH_LONG).show()
        Log.e("DAO-Filme", "Atualizar Filme")
        doAsync {
            Log.e("DAO-Filme", "doAsync")
            filmeEdit?.let { FilmeService.deletar(it) }
            FilmeService.inserir(filme)

            uiThread {
                Log.e("DAO-Filme", "Atualizar Filme: " + filme.toString())
            }
        }
        TimeUnit.SECONDS.sleep(1L)
    }
}
