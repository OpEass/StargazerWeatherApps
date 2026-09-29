package com.example.stargazerweatherapp

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var videoViewBg: FullScreenVideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        videoViewBg = findViewById(R.id.videoViewBg)

        // 1. SET TINGGI AREA TRANSPARAN MENYESUAIKAN TINGGI LAYAR HP
        val realScreenHeight = getRealScreenHeight()
        val transparentSpace = findViewById<View>(R.id.transparentSpace)
        transparentSpace.layoutParams = transparentSpace.layoutParams.apply {
            height = realScreenHeight
        }

        // 2. SETUP VIDEO BACKGROUND FULLSCREEN & LOOPING
        setupBackgroundVideo()

        // 3. SETUP NAVIGASI & KLIK TOMBOL
        setupClickListeners()
    }

    private fun setupBackgroundVideo() {
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.weather_bg)
        videoViewBg.setVideoURI(videoUri)

        videoViewBg.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            mediaPlayer.setVolume(0f, 0f) // Mute suara video

            // Menyesuaikan rasio video ke Custom VideoView
            videoViewBg.setVideoSize(mediaPlayer.videoWidth, mediaPlayer.videoHeight)
            videoViewBg.start()
        }

        // Mencegah aplikasi crash jika file video gagal dimuat
        videoViewBg.setOnErrorListener { _, _, _ ->
            true
        }
    }

    private fun setupClickListeners() {
        // Tombol Header / Top Bar
        findViewById<ImageButton>(R.id.btnRefresh)?.setOnClickListener {
            Toast.makeText(this, "Memperbarui data cuaca...", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageButton>(R.id.btnAdd)?.setOnClickListener {
            Toast.makeText(this, "Tambah Lokasi", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageButton>(R.id.btnSettings)?.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        // Area Informasi Kota / Suhu Utama
        findViewById<View>(R.id.llLocation)?.setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }

        // Tombol Tambah Aktivitas Outdoor & Indoor
        findViewById<CardView>(R.id.btnAddOutdoor)?.setOnClickListener {
            startActivity(Intent(this, OutdoorActivity::class.java))
        }

        findViewById<CardView>(R.id.btnAddIndoor)?.setOnClickListener {
            startActivity(Intent(this, IndoorActivity::class.java))
        }
    }

    // FUNGSI MENGHITUNG TINGGI FISIK LAYAR HP LENGKAP (TERMASUK STATUS & NAV BAR)
    private fun getRealScreenHeight(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics
            windowMetrics.bounds.height()
        } else {
            val display = windowManager.defaultDisplay
            val metrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            display.getRealMetrics(metrics)
            metrics.heightPixels
        }
    }

    override fun onResume() {
        super.onResume()
        if (!videoViewBg.isPlaying) {
            videoViewBg.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (videoViewBg.isPlaying) {
            videoViewBg.pause()
        }
    }
}