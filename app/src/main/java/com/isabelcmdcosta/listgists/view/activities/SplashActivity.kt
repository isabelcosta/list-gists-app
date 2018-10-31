package com.isabelcmdcosta.listgists.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.isabelcmdcosta.listgists.R

/**
 * This activity will show the logo for short time and then open main screen
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var SPLASH_DISPLAY_LENGTH: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, ListGistsActivity::class.java)

        runnable = Runnable {
            startActivity(intent)
            finish()
        }
        handler = Handler()
        handler.postDelayed(runnable, SPLASH_DISPLAY_LENGTH)
    }

    override fun onDestroy() {
        super.onDestroy()

        handler.removeCallbacks(runnable)
    }
}
