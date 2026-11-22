package org.fossify.filemanager.extensions

import android.content.Context

fun String.isZipFile() = endsWith(".zip", true)

fun String.isPathInHiddenFolder(): Boolean {
    val parts = split("/")
    for (i in 1 until parts.size - 1) {
        val part = parts[i]
        val isHidden = part.startsWith(".") && part != "." && part != ".." && part.isNotEmpty()
        if (isHidden) {
            return true
        }
    }
    return false
}

/**
 * Converts ASCII digits to Persian digits when the app is using Persian.
 */
fun Int.toLocalizedDigits(context: Context): String {
    if (context.resources.configuration.locales[0].language != "fa") {
        return toString()
    }

    return toString().map { character ->
        if (character in '0'..'9') {
            ('۰'.code + (character.code - '0'.code)).toChar()
        } else {
            character
        }
    }.joinToString("")
}
