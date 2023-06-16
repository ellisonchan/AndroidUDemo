package com.ellison.demo.personalization

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView

class GrammarGenderTextView : AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onConfigurationChanged(newConfig: Configuration) {
        Log.d("Gender", "GrammarGenderTextView# onConfigurationChanged()" +
                " new gender:${newConfig.grammaticalGender.genderToString()}" +
                " current text:$text"
        )
        super.onConfigurationChanged(newConfig)
    }
}
