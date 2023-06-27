package com.ellison.demo.path

import android.graphics.Path
import android.graphics.PathIterator
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class PathActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val path = Path().apply {
            moveTo(1.0f, 1.0f)
            lineTo(2.0f, 2.0f)
            close()
        }

        val pathIterator = path.pathIterator

        for (segment in pathIterator) {
            Log.i("Path", "segment action: ${segment.verb.toPathAction()}")

            for (f in segment.points) {
                Log.i("Path", "point: $f")
            }
        }
    }

    private fun Int.toPathAction() =
        when (this) {
            PathIterator.VERB_MOVE  -> "VERB_MOVE"
            PathIterator.VERB_LINE  -> "VERB_LINE"
            PathIterator.VERB_QUAD  -> "VERB_QUAD"
            PathIterator.VERB_CONIC -> "VERB_CONIC"
            PathIterator.VERB_CUBIC -> "VERB_CUBIC"
            PathIterator.VERB_CLOSE -> "VERB_CLOSE"
            PathIterator.VERB_DONE  -> "VERB_DONE"
            else                    -> "VERB_DONE"
        }
}