package com.ellison.demo.recognition.service.provider

import android.speech.RecognitionService

class CerenceRecognitionEngine : IRecognitionEngine {
    override fun init() {
//        Context attributionContext = context.createContext(new ContextParams.Builder()
//            .setNextAttributionSource(callback.getCallingAttributionSource())
//            .build());
//
//        AudioRecord recorder = AudioRecord.Builder()
//            .setContext(attributionContext);
//        . . .
//        .build();
//
//        recorder.startRecording()
    }

    override fun startASR(parameter: String, callback: RecognitionService.Callback?) {
        // Todo invoke beginningOfSpeech(), endOfSpeech(), partialResults() ...
    }

    override fun stopASR(callback: RecognitionService.Callback?) {
        // Todo invoke results()...
    }

    override fun release(callback: RecognitionService.Callback?) {
        // Todo invoke event()...
    }
}