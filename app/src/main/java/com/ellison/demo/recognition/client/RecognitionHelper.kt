package com.ellison.demo.recognition.client

import android.content.Context
import android.os.Bundle
import android.content.Intent
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import java.util.Locale

class RecognitionHelper(private val context: Context) : RecognitionListener {
    private lateinit var recognizer: SpeechRecognizer

    private lateinit var mResultListener: ASRResultListener

    fun prepareRecognition(resultListener: ASRResultListener): Boolean {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            Log.e("RecognitionHelper", "System has no recognition service yet.")
            return false
        }

        recognizer = SpeechRecognizer.createSpeechRecognizer(context)
        // recognizer = SpeechRecognizer.createOnDeviceSpeechRecognizer(context)

        recognizer.setRecognitionListener(this)

        mResultListener = resultListener

        return true
    }

    fun startRecognition() {
        val intent = createRecognitionIntent()
        recognizer.startListening(intent)

//        recognizer.checkRecognitionSupport(
//            intent,
//            context.mainExecutor,
//            object : RecognitionSupportCallback {
//                override fun onSupportResult(recognitionSupport: RecognitionSupport) {
//                    Log.d("RecognitionHelper", "Current intent is supported," +
//                            " with supportedOnDeviceLanguages:${recognitionSupport.supportedOnDeviceLanguages}" +
//                            " with installedOnDeviceLanguages:${recognitionSupport.installedOnDeviceLanguages}" +
//                            " with onlineLanguages:${recognitionSupport.onlineLanguages}" +
//                            " with pendingOnDeviceLanguages:${recognitionSupport.pendingOnDeviceLanguages}"
//                    )
//
////                    if (!recognitionSupport.installedOnDeviceLanguages.isEmpty()) {
////                        recognizer.startListening(intent)
////                    }
//                }
//
//                override fun onError(errorCode: Int) {
//                    Log.e("RecognitionHelper", "Current intent is not supported with error:$errorCode")
//                    // SpeechRecognizer#ERROR_XXX
//                }
//            }
//        )
    }

    fun stopRecognition() {
        recognizer.stopListening()
    }

    fun releaseRecognition() {
        recognizer.cancel()
        recognizer.destroy()
    }

    override fun onReadyForSpeech(p0: Bundle?) {
        Log.d("RecognitionHelper", "onReadyForSpeech()")
    }

    override fun onBeginningOfSpeech() {
        Log.d("RecognitionHelper", "onBeginningOfSpeech()")
    }

    override fun onRmsChanged(p0: Float) {
        Log.d("RecognitionHelper", "onRmsChanged() with:$p0")
    }

    override fun onBufferReceived(p0: ByteArray?) {
        Log.d("RecognitionHelper", "onBufferReceived() with:$p0")
    }

    override fun onEndOfSpeech() {
        Log.d("RecognitionHelper", "onEndOfSpeech()")
    }

    override fun onError(p0: Int) {
        Log.d("RecognitionHelper", "onError() with error:$p0")
    }

    override fun onPartialResults(bundle: Bundle?) {
        bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.let {
            Log.d(
                "RecognitionHelper", "onPartialResults() with:$bundle" +
                        " results:$it"
            )

            mResultListener.onPartialResult(it[0])
        }
    }

    override fun onResults(bundle: Bundle?) {
        bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.let {
            Log.d(
                "RecognitionHelper", "onResults() with:$bundle" +
                        " results:$it"
            )

            mResultListener.onFinalResult(it[0])
        }
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
        Log.d("RecognitionHelper", "onEvent() with code:$p0 content:$p1")
    }
}

fun createRecognitionIntent() = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
    putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)
    putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
}