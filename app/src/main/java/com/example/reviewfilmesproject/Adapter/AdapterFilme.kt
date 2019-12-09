package com.example.reviewfilmesproject.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewfilmesproject.FilmeDetalheActivity
import com.example.reviewfilmesproject.Holder.ViewHolderFilme
import com.example.reviewfilmesproject.MainActivity
import com.example.reviewfilmesproject.Model.Filme
import com.example.reviewfilmesproject.R

class AdapterFilme (var context: Context, var listFilmes : ArrayList<Filme>) : RecyclerView.Adapter<ViewHolderFilme>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilme {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_filme, parent, false)
        return ViewHolderFilme(itemView)
    }

    override fun getItemCount(): Int {
        return listFilmes.size
    }

    override fun onBindViewHolder(holder: ViewHolderFilme, position: Int) {
        var filme = listFilmes[position]
        holder.bindView(filme)

        holder.itemView.setOnClickListener{
            var i = Intent(context, FilmeDetalheActivity::class.java)
            i.putExtra("filme", filme)
            context.startActivity(i)
        }
    }
}