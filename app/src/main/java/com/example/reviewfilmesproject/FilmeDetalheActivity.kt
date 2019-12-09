package com.example.reviewfilmesproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.reviewfilmesproject.Model.Filme
import com.example.reviewfilmesproject.Service.FilmeService
import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FilmeDetalheActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_CODE = 2
    }

    lateinit var imageDet : ImageView
    lateinit var titleDet : TextView
    lateinit var descricaoDet : TextView
    lateinit var notaDet : TextView
    var filme : Filme? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filme_detalhe)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Detalhe Filmes"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        titleDet = findViewById(R.id.titleDet)
        descricaoDet = findViewById(R.id.descricaoDet)
        imageDet = findViewById(R.id.imageDet)
        notaDet = findViewById(R.id.notaDet)
        filme = intent.getParcelableExtra<Filme>("filme")

        if(filme != null){
            Picasso.get().load(filme?.urlImagem).into(imageDet)
            titleDet.text = filme?.titulo
            descricaoDet.text = filme?.sinopse
            notaDet.text = filme?.nota.toString() + " / 5.0"
        }else{
            Log.e("FilmeDetalhe", "Filme não existe")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_filme_detalhe, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        when(item.itemId) {
            R.id.itemMenuEdit -> editMovie()
        }
        when(item.itemId) {
            R.id.itemMenuDelete -> filme?.let { deletar(it) }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE && resultCode == FilmeAtualizarActivity.RESULT_CODE_REGISTER){
            var filme = data?.getParcelableExtra<Filme>("filme")
            if(filme != null){
                Picasso.get().load(filme?.urlImagem).into(imageDet)
                titleDet.text = filme?.titulo
                descricaoDet.text = filme?.sinopse
                notaDet.text = filme?.nota.toString() + " / 5.0"
            }else{
                Log.e("MainActivity", "Error ao atualizar Filme")
            }
        }
    }

    fun editMovie() {
        var i = Intent(ExibirActivity@this, FilmeAtualizarActivity::class.java)
        i.putExtra("filme", filme)
        startActivityForResult(i, REQUEST_CODE)
    }

    fun deletar(filme: Filme){

        doAsync {
            FilmeService.deletar(filme)

            uiThread {
                Log.e("DAO-Filme", "Deletar Filme: " + filme.toString())
            }
        }
        finish()

    }

}
