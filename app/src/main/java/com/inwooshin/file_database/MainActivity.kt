package com.inwooshin.file_database

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.inwooshin.file_database.databinding.ActivityMainBinding

val binding by lazy { ActivityMainBinding.inflate(LayoutInflater) }

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun checkPermission(){
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)


    }
}