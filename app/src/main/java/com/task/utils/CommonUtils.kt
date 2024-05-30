package com.task.utils

import android.os.Build
import android.text.Html
import android.text.Spanned

object CommonUtils {

    fun htmlRender(htmlContent: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(htmlContent)
        }
    }
}