package com.example.reviewfilmesproject.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "filme")
class Filme() : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
    var urlImagem: String? = null
    var titulo: String? = null
    var sinopse: String? = null
    var nota: Double? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        urlImagem = parcel.readString()
        titulo = parcel.readString()
        sinopse = parcel.readString()
        nota = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(urlImagem)
        parcel.writeString(titulo)
        parcel.writeString(sinopse)
        nota?.let { parcel.writeDouble(it) }

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Filme(id=$id, urlImagem=$urlImagem, titulo=$titulo, sinopse=$sinopse, nota=$nota)"
    }

    companion object CREATOR : Parcelable.Creator<Filme> {
        override fun createFromParcel(parcel: Parcel): Filme {
            return Filme(parcel)
        }

        override fun newArray(size: Int): Array<Filme?> {
            return arrayOfNulls(size)
        }
    }

}