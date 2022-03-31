package com.example.codechallengeaomata.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.codechallengeaomata.R
import kotlinx.android.synthetic.main.activity_full_screen.*

class FullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        var i = intent
        var image = i.getStringExtra("image").toString()

        Glide.with(this)
            .load(image)
            .dontAnimate()
            .into(img)
    }
}