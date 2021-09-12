package com.ooooonly.onote.utils

import java.text.DateFormat
import java.util.*

fun Date.prettyFormat(): String {
    return DateFormat.getInstance().format(this)
}