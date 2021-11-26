package ru.intelligency.scholarship.presentation.utils

import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import ru.intelligency.scholarship.R

object ClickableSpanStringHelper {

    /**
     * @param startIndex is inclusive
     * @param endIndex is exclusive
     */
    fun makeTextClickable(
        textView: TextView,
        isUnderlineText: Boolean,
        startIndex: Int,
        endIndex: Int,
        @ColorRes colorRes: Int = R.color.primary_blue,
        onClick: (View) -> Unit
    ) {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                onClick(widget)
            }
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = isUnderlineText
            }
        }
        val color = ContextCompat.getColor(textView.context, colorRes)
        val transparentHighlightColor = ContextCompat.getColor(
            textView.context,
            android.R.color.transparent
        )
        val spanString = SpannableString(textView.text).apply {
            setSpan(clickableSpan, startIndex, endIndex, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
            setSpan(
                ForegroundColorSpan(color),
                startIndex,
                endIndex,
                SpannableString.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }

        textView.apply {
            highlightColor = transparentHighlightColor
            text = spanString
            movementMethod = LinkMovementMethod.getInstance()
        }
    }
}
