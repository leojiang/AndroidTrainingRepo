package com.tw.training.catkeeper.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.R.id.btn_login
import com.tw.training.catkeeper.utils.AnimationUtil
import com.tw.training.catkeeper.utils.KeyboardWatcher
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), KeyboardWatcher.SoftKeyboardStateListener {
    private var screenHeight = 0

    private lateinit var mBtnLogin: View
    private lateinit var mLogoLayout: View
    private lateinit var mContent: View
    private lateinit var keyboardWatcher: KeyboardWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLogoLayout = findViewById(R.id.logo_layout)
        mBtnLogin = findViewById(R.id.btn_login)
        mContent = findViewById(R.id.content)

        screenHeight = resources.displayMetrics.heightPixels
        keyboardWatcher = KeyboardWatcher(findViewById(Window.ID_ANDROID_CONTENT))
        keyboardWatcher.addSoftKeyboardStateListener(this)

        // launch MainActivity
        mBtnLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        keyboardWatcher.removeSoftKeyboardStateListener(this)
    }

    override fun onSoftKeyboardOpened(keyboardHeightInPx: Int) {
        val location = IntArray(2)
        mContent.getLocationOnScreen(location)
        val bottom = screenHeight - (location[1] + mContent.height)

        if(keyboardHeightInPx > bottom) {
            val dist = (keyboardHeightInPx - bottom).toFloat()

            AnimationUtil.moveContentUp(mContent, dist)
            AnimationUtil.zoomIn(mLogoLayout, dist)
        }
    }

    override fun onSoftKeyboardClosed() {
        AnimationUtil.moveContentDown(mContent)
        AnimationUtil.zoomOut(mLogoLayout)
    }
}
