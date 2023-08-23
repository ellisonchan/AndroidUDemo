package com.ellison.demo.recognition.client

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import android.Manifest
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.ellison.demo.databinding.RecognitionLayoutBinding

class RecognitionActivity : AppCompatActivity(), ASRResultListener {
    private lateinit var binding: RecognitionLayoutBinding

    private val recognitionHelper: RecognitionHelper by lazy {
        RecognitionHelper(this)
    }

//    private var recognitionTime: Long = 0L

    private var updatingTextTimeDelayed = 0L

    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RecognitionHelper", "onCreate()")

        binding = RecognitionLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
            Log.e("RecognitionHelper", "audio recording permission denied & request")

            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.RECORD_AUDIO),
                100
            )
        } else {
            Log.e("RecognitionHelper", "audio recording permission granted")
        }

        if (!recognitionHelper.prepareRecognition(this)) {
            Toast.makeText(this, "Recognition not available", Toast.LENGTH_SHORT).show()

            return
        }

        binding.start.setOnClickListener {
            Log.d("RecognitionHelper", "startRecognition()")
            recognitionHelper.startRecognition()

//            recognitionTime = System.currentTimeMillis()
        }

        binding.stop.setOnClickListener {
            Log.d("RecognitionHelper", "stopRecognition()")
            recognitionHelper.stopRecognition()

//            recognitionTime = 0L
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("RecognitionHelper", "onStart()")

//        if (recognitionHelper.prepareRecognition()) {
//            recognitionHelper.startRecognition()
//        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("RecognitionHelper", "onPause()")

//        recognitionHelper.stopRecognition()
    }

    override fun onStop() {
        super.onStop()
        Log.d("RecognitionHelper", "onStop()")

        recognitionHelper.releaseRecognition()

//        recognitionTime = 0L
    }

    override fun onPartialResult(result: String) {
        Log.d("RecognitionHelper", "onPartialResult() with result:$result")

//        recognitionTime += 500L
//        mainHandler.postAtTime(
//            {
//                Log.d("RecognitionHelper", "onPartialResult() updating")
//                binding.recoAsr.text = result
//            }, recognitionTime
//        )

        updatingTextTimeDelayed += 300L
        mainHandler.postDelayed(
            {
                Log.d("RecognitionHelper", "onPartialResult() updating")
                binding.recoAsr.text = result
            }, updatingTextTimeDelayed
        )


//        MainScope().launch {
//            delay(500)
//
//            Log.d("RecognitionHelper", "onPartialResult() updating")
//            binding.recoAsr.text = result
//        }
    }

    override fun onFinalResult(result: String) {
        Log.d("RecognitionHelper", "onFinalResult() with result:$result")

        updatingTextTimeDelayed += 300L
        mainHandler.postDelayed(
            {
                Log.d("RecognitionHelper", "onFinalResult() updating")
                binding.recoAsr.text = result
            }, updatingTextTimeDelayed
        )

//        recognitionTime += 500L
//        mainHandler.postAtTime(
//            {
//                Log.d("RecognitionHelper", "onFinalResult() updating")
//                binding.recoAsr.text = result
//            }, recognitionTime + 500L
//        )

//        MainScope().launch {
//            delay(1000)
//
//            Log.d("RecognitionHelper", "onFinalResult() updating")
//            binding.recoAsr.text = result
//        }
    }
}