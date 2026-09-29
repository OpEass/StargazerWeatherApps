package com.example.stargazerweatherapp

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView

class FullScreenVideoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : VideoView(context, attrs, defStyleAttr) {

    private var videoWidth = 0
    private var videoHeight = 0

    // Fungsi untuk menerima ukuran asli video
    fun setVideoSize(width: Int, height: Int) {
        videoWidth = width
        videoHeight = height
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val viewWidth = getDefaultSize(videoWidth, widthMeasureSpec)
        val viewHeight = getDefaultSize(videoHeight, heightMeasureSpec)

        if (videoWidth > 0 && videoHeight > 0) {
            // Algoritma Center-Crop: Memperbesar video hingga menutupi seluruh layar tanpa garis hitam
            if (videoWidth * viewHeight > viewWidth * videoHeight) {
                val scaledWidth = viewHeight * videoWidth / videoHeight
                setMeasuredDimension(scaledWidth, viewHeight)
            } else {
                val scaledHeight = viewWidth * videoHeight / videoWidth
                setMeasuredDimension(viewWidth, scaledHeight)
            }
        } else {
            setMeasuredDimension(viewWidth, viewHeight)
        }
    }
}