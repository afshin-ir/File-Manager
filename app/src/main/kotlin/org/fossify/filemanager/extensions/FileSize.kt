package org.fossify.filemanager.extensions

import android.content.Context
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
    val language = context.resources.configuration.locales[0].language
    if (language != "fa") return formatted

    val match = Regex("""^(.+?)\s+(B|kB|KiB|MB|MiB|GB|GiB|TB|TiB|PB|PiB|EB|EiB)$""")
        .matchEntire(formatted) ?: return formatted
    val number = match.groupValues[1]
    val unit = persianFileSizeUnits[match.groupValues[2]] ?: return formatted

    // Keep the numeric part left-to-right while the Persian unit follows RTL direction.
    return "\u200E$number\u200E $unit"
}
