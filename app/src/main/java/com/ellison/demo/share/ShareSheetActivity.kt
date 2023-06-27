package com.ellison.demo.share

import android.app.PendingIntent
import android.app.SearchManager
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.service.chooser.ChooserAction
import androidx.appcompat.app.AppCompatActivity
import com.ellison.demo.R

class ShareSheetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pendingIntent: PendingIntent =PendingIntent.getActivity(
            this@ShareSheetActivity,
            0,
            Intent(Intent.ACTION_WEB_SEARCH).apply {
                putExtra(SearchManager.QUERY, "Search on web 🌐.")
            },
            PendingIntent.FLAG_IMMUTABLE
        )

        val chooserAction = ChooserAction.Builder(
            Icon.createWithResource(this@ShareSheetActivity, R.drawable.ic_launcher_foreground),
            "Send to Ellison",
            pendingIntent
        ).build()

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        shareIntent.putExtra(Intent.EXTRA_CHOOSER_CUSTOM_ACTIONS, arrayOf(chooserAction))

        startActivity(shareIntent)
    }
}