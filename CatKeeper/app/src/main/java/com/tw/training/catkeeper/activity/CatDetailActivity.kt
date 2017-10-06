package com.tw.training.catkeeper.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.widget.ImageView
import com.tw.training.catkeeper.R

class CatDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_detail)

        val imageView = findViewById<ImageView>(R.id.avatar)
        ViewCompat.setTransitionName(imageView, "avatar_transition")
    }
}
