package com.ellison.demo.recognition.service.provider

import android.speech.RecognitionService.Callback

interface IRecognitionEngine {
    fun init()

    fun startASR(parameter: String, callback: Callback?)

    fun stopASR(callback: Callback?)

    fun release(callback: Callback?)
}