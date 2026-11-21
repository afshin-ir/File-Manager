package org.fossify.filemanager.extensions

import android.content.Context
import android.text.BidiFormatter
import org.fossify.commons.extensions.formatSize

private val persianFileSizeUnits = mapOf(
    "B" to "بایت",
    "kB" to "کیلوبایت",
    "KiB" to "کیلوبایت",
    "MB" to "مگابایت",
    "MiB" to "مگابایت",
    "GB" to "گیگابایت",
    "GiB" to "گیگابایت",
    "TB" to "ترابایت",
    "TiB" to "ترابایت",
    "PB" to "پتابایت",
    "PiB" to "پتابایت",
    "EB" to "اگزابایت",
    "EiB" to "اگزابایت"
)

fun Long.formatLocalizedFileSize(context: Context): String {
    val formatted = formatSize()
    val locale = context.resources.configuration.locales[0]
    if (locale.language != "fa") return formatted

    val match = Regex("""^(.+?)\s+(B|kB|KiB|MB|MiB|GB|GiB|TB|TiB|PB|PiB|EB|EiB)$""")
        .matchEntire(formatted) ?: return formatted
    val number = match.groupValues[1]
    val unit = persianFileSizeUnits[match.groupValues[2]] ?: return formatted

    return BidiFormatter.getInstance(locale).unicodeWrap(number + "\u00A0" + unit)
}
