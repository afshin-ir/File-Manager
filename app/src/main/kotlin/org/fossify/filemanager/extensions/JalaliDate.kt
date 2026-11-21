package org.fossify.filemanager.extensions

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.ULocale
import org.fossify.commons.extensions.baseConfig
import org.fossify.commons.extensions.getTimeFormat
import java.util.Date
import java.util.Locale

fun Long.formatJalaliDate(
    context: Context,
    dateFormat: String? = null,
    timeFormat: String? = null,
): String {
    val locale = ULocale.forLocale(Locale.forLanguageTag("fa-IR"))
        .setKeywordValue("calendar", "persian")
    val pattern = "${dateFormat ?: context.baseConfig.dateFormat}, ${timeFormat ?: context.getTimeFormat()}"
    return SimpleDateFormat(pattern, locale).format(Date(this))
}
