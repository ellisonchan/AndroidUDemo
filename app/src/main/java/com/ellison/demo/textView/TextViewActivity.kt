package com.ellison.demo.textView

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.text.Highlights
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ellison.demo.databinding.TextviewLayoutBinding

class TextViewActivity : AppCompatActivity() {
    companion object {
        const val TEXT = "val builder = Highlights.Builder()"
        const val TEXT_LONG = "val builder = Highlights.Builder()val val val val val val val val "
    }

    var textView1Highlights: Highlights? = null

//    var textViewHighlightsForSearch: Highlights? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = TextviewLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val greenPaint = Paint().apply {
            color = Color.GREEN
//            Log.d("HighLights", "Color.GREEN:${Color.GREEN}" +
//                    " paint color:${this.color}")
        }
//        val redPaint = Paint().apply {
//            color = Color.RED
//        }
        val yellowPaint = Paint().apply {
            color = Color.YELLOW
//            Log.d("HighLights", "Color.YELLOW:${Color.YELLOW}" +
//                    " paint color:${this.color}")
        }

        with(binding.textview1) {
            text = TEXT
            val builder = Highlights.Builder()
                .addRange(yellowPaint, 0, 3)
                .addRange(greenPaint, 14, 24)
                .addRange(greenPaint, 25, 32)
            highlights = builder.build()
        }

        with(binding.textview2) {
            text = TEXT_LONG

            // set highlight color for searching
            searchResultHighlightColor = Color.CYAN

            // set matched searching highlight color
            focusedSearchResultHighlightColor = Color.GRAY
        }

        textView1Highlights = binding.textview1.highlights?.apply {
            Log.d("HighLights", "textview1 usedHighLights' size:$size")

            for (i in 0 until size) {
                Log.d("HighLights", "usedHighLights'" +
                        " paint:${getPaint(i).color.toColorString()}")
                val range = getRanges(i)
                for (j in range.indices) {
                    Log.d("HighLights", "ranges:${range[j]}")
                }
            }
        }

//        binding.textview2.highlights?.run {
//            Log.d("HighLights", "textview2 usedHighLights' size:$size")
//
//            for (i in 0 until size) {
//                Log.d("HighLights", "usedHighLights'" +
//                        " paint:${getPaint(i).color.toColorString()}")
//                val range = getRanges(i)
//                for (j in range.indices) {
//                    Log.d("HighLights", "ranges:${range[j]}")
//                }
//            }
//        }

        binding.changeHighlights.setOnClickListener {
            Log.d("HighLights", "changeHighlights tapped & change highlights")
            textView1Highlights?.apply {
                // Change color
                getPaint(1).color = Color.BLUE
                // Change range
                getRanges(1)[0] -= 3
                getRanges(1)[1] += 1

                Log.d("HighLights", "textview1 textView1Highlights' size:$size")
                for (i in 0 until size) {
                    Log.d("HighLights", "textView1Highlights'" +
                            " paint:${getPaint(i).color.toColorString()}")
                    val range = getRanges(i)
                    for (j in range.indices) {
                        Log.d("HighLights", "ranges:${range[j]}")
                    }
                }
            }

            binding.textview1.invalidate()
//
//            textView1Highlights?.apply {
//                Log.d("HighLights", "textview1 textView1Highlights' size:$size")
//                for (i in 0 until size) {
//                    Log.d("HighLights", "textView1Highlights'" +
//                            " paint:${getPaint(i).color.toColorString()}")
//                    val range = getRanges(i)
//                    for (j in range.indices) {
//                        Log.d("HighLights", "ranges:${range[j]}")
//                    }
//                }
//            }
        }

        binding.startSearch.setOnClickListener {
            binding.textview2.run {
                Log.d("HighLights", "startSearch tapped" +
                        " and current Focused Color:${focusedSearchResultHighlightColor.toColorString()}" +
                        " and currentFocusedResultIndex:${focusedSearchResultIndex}")

                // set searching ranges
                setSearchResultHighlights(4, 10, 25, 32)
            }
        }

        binding.changeSearchIndex.setOnClickListener {
            binding.textview2.run {
                Log.d("HighLights", "changeSearchIndex tapped" +
                        " and current Focused Color:${focusedSearchResultHighlightColor.toColorString()}" +
                        " and currentFocusedResultIndex:${focusedSearchResultIndex}")

                // Set index to first or second
                val newSearchIndex = when (focusedSearchResultIndex) {
                    TextView.FOCUSED_SEARCH_RESULT_INDEX_NONE, 1 -> 0
                    0 -> 1
                    else -> TextView.FOCUSED_SEARCH_RESULT_INDEX_NONE
                }

                Log.d("HighLights", "changeSearchIndex to :$newSearchIndex")
                binding.textview2.focusedSearchResultIndex = newSearchIndex
            }
        }

        binding.changeLineHeight.setOnClickListener {
            binding.textview2.run {
                Log.d(
                    "HighLights", "changeLineHeight tapped" +
                            " current line Height:$lineHeight"
                )

                // setLineHeight(TypedValue.COMPLEX_UNIT_DIP, 20)
                lineHeight = 80

                Log.d(
                    "HighLights", "new line Height:$lineHeight"
                )
            }
        }
    }

    // private fun Long.rgbaToArgb() = (this shr 32 xor 0xffffffffL).toInt()
}

fun Int.toColorString() =
    when (this) {
        -27086    -> "CYAN"
        -16711936 -> "GREEN"
        -256      -> "YELLOW"
        -7829368  -> "GREY"
        -16776961 -> "BLUE"
        else      -> "NONE"
    }