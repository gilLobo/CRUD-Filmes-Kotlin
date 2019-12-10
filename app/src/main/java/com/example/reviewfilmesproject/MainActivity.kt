package com.example.reviewfilmesproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewfilmesproject.Adapter.AdapterFilme
import com.example.reviewfilmesproject.Model.Filme
import com.example.reviewfilmesproject.Service.FilmeService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_CODE = 1
    }

    lateinit var recycleViewFilme : RecyclerView
    lateinit var fabRegister : FloatingActionButton
    lateinit var listFilmes : ArrayList<Filme>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adicionando toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Projeto Movie"

        recycleViewFilme = findViewById(R.id.recyclerView)
        fabRegister = findViewById(R.id.fabRegister)

        // Retornando lista de filmes da API ROOM
        listFilmes = listarTodos()

        fabRegister.setOnClickListener{
            var i = Intent(MainActivity@this, RegistrarFilmeActivity::class.java)
            startActivityForResult(i, REQUEST_CODE)
        }

    }

    override fun onStart() {
        super.onStart()
        listarTodos()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE && resultCode == RegistrarFilmeActivity.RESULT_CODE_REGISTER){
            var filme = data?.getParcelableExtra<Filme>("filme")
            if(filme != null){
                listFilmes.add(filme)
                recycleViewFilme.adapter!!.notifyDataSetChanged()
            }else{
                Log.e("MainActivity", "Error ao retornar Filme")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemMenuAbout -> Toast.makeText(MainActivity@this, "About app.", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun configureListInReciclerView(filmes: ArrayList<Filme>) {
        recycleViewFilme.adapter = AdapterFilme(this@MainActivity, filmes)
        recycleViewFilme.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    open fun listarTodos():ArrayList<Filme> {
        var lista = ArrayList<Filme>()
        doAsync {
            var filmes = FilmeService.listarTodas()
            uiThread {
                for (filme in filmes) {
                    lista.add(filme)
                }
                configureListInReciclerView(lista)
                recycleViewFilme.adapter!!.notifyDataSetChanged()
            }
        }
        return lista
    }
}
