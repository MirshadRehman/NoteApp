package com.mirshad.myapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mirshad.myapp.MainActivity
import com.mirshad.myapp.R

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 5000 // 3 seconds delay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val tvAppName = findViewById<TextView>(R.id.tv_note)

        // Load the animation
        val zoomInAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in_animation)

        // Set listener to start MainActivity after animation ends
        zoomInAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                // Handler to delay the transition to MainActivity
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        // Start animation
        tvAppName.startAnimation(zoomInAnimation)

    }
}
