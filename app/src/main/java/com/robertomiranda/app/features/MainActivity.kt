package com.robertomiranda.app.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import com.robertomiranda.app.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEmoji()
    }

    private fun initEmoji() {
        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)
    }
}
