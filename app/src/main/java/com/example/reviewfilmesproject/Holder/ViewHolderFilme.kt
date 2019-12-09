package com.example.reviewfilmesproject.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewfilmesproject.Model.Filme
import com.example.reviewfilmesproject.R
import com.squareup.picasso.Picasso

class ViewHolderFilme (var item : View) : RecyclerView.ViewHolder(item) {

    fun bindView(filme : Filme){
        var imageIv = item.findViewById<ImageView>(R.id.imageIv)
        var textTitulo = item.findViewById<TextView>(R.id.titleTv)
        var textSinopse = item.findViewById<TextView>(R.id.sinopseTv)

        Picasso.get().load(filme.urlImagem).into(imageIv)
        textTitulo.text = filme.titulo
        textSinopse.text = filme.sinopse
    }

}