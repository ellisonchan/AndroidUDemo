package com.ellison.demo.recognition.service.provider

object RecognitionProvider {
    fun provideRecognition(): IRecognitionEngine = CerenceRecognitionEngine()
}