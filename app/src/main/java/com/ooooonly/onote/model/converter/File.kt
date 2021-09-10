package com.ooooonly.onote.model.converter

import androidx.room.TypeConverter
import java.io.File

class FileConverter {
    @TypeConverter
    fun fromPath(value: String?): File? {
        return value?.let { File(it) }
    }

    @TypeConverter
    fun fileToPath(file: File?): String? {
        return file?.absolutePath
    }
}