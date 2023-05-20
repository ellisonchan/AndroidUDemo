package com.ellison.demo.capture

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ellison.demo.databinding.ScreenShotBinding

class ScreenShotActivity : AppCompatActivity() {
    private val screenCaptureCallback = ScreenCaptureCallback {
        Log.d("ScreenShot", "onScreenCaptured()", Throwable())

//        Toast.makeText(
//            this,
//            "You have taken a screenshot of the current screen, be careful to protect your privacy and do not share it with others.",
//            Toast.LENGTH_LONG
//        ).show()

        AlertDialog.Builder(this)
            .setMessage("You have taken a screenshot of the current screen, be careful to protect your privacy and do not share it with others.")
            .setTitle("Warning")
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ScreenShotBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        Log.d("ScreenShot", "onStart() registerScreenCaptureCallback")
        registerScreenCaptureCallback(mainExecutor, screenCaptureCallback)
    }

    override fun onStop() {
        super.onStop()

        Log.d("ScreenShot", "onStop() unregisterScreenCaptureCallback")
        unregisterScreenCaptureCallback(screenCaptureCallback)
    }
}