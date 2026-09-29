package com.example.stargazerweatherapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OutdoorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_outdoor)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvTitleOutdoor)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Hanya memberi padding atas agar judul tidak tertutup status bar baterai
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }

        // --- LOGIKA TOMBOL KEMBALI ---
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // Perintah 'finish()' akan menutup halaman ini dan mengembalikan Anda ke MainActivity
        }
    }
}