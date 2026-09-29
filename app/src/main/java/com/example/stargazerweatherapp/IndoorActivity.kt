package com.example.stargazerweatherapp

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IndoorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_indoor)

        // Mengatur padding agar judul tidak terpotong status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvTitleIndoor)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }

        // 1. TOMBOL KEMBALI TO MAIN ACTIVITY
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        // 2. CONTOH RESPON LOGIKA TOMBOL TAMBAH/HAPUS
        val btnToggleYoga = findViewById<ImageButton>(R.id.btnToggleYoga)
        btnToggleYoga.setOnClickListener {
            Toast.makeText(this, "Yoga dihapus dari beranda", Toast.LENGTH_SHORT).show()
        }

        val btnToggleMenonton = findViewById<ImageButton>(R.id.btnToggleMenonton)
        btnToggleMenonton.setOnClickListener {
            Toast.makeText(this, "Menonton ditambahkan ke beranda", Toast.LENGTH_SHORT).show()
        }
    }
}