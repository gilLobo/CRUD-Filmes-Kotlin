package com.example.reviewfilmesproject

import android.app.Application
import android.util.Log

class FilmeApplication : Application() {

    private val TAG = "FilmeApplication"

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object{
        private var appInstance : FilmeApplication? = null
        fun getInstance():FilmeApplication{
            if(appInstance == null){
                throw ExceptionInInitializerError("Informe a classe no arquivo do Manifest")
            }else{
                return appInstance!!
            }
        }

    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "FilmeApplication.onTerminate()")
    }
}