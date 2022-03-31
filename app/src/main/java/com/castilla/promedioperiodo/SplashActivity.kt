package com.castilla.promedioperiodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {
    var logo:ImageView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        Handler().postDelayed(Runnable {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        },3000)
    }
}