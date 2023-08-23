package com.ellison.demo.recognition.client

interface ASRResultListener {
    fun onPartialResult(result: String)

    fun onFinalResult(result: String)
}