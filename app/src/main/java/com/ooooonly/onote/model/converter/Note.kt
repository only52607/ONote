package com.ooooonly.onote.model.converter

import androidx.room.TypeConverter
import com.ooooonly.onote.model.entity.NotePackageType
import com.ooooonly.onote.model.entity.NoteType
import java.lang.IllegalArgumentException

class NoteTypeConverter {
    companion object {
        const val MARKDOWN = 0
    }

    @TypeConverter
    fun fromInt(value: Int?): NoteType? {
        return value?.let {
            when(it) {
                MARKDOWN -> NoteType.MARK_DOWN
                else -> throw IllegalArgumentException("Unknown note type $it")
            }
        }
    }

    @TypeConverter
    fun toInt(type: NoteType?): Int? {
        return type?.let {
            when(it) {
                NoteType.MARK_DOWN -> MARKDOWN
            }
        }
    }
}

class NotePackageTypeConverter {
    companion object {
        const val PUBLIC = 0
        const val ENCRYPT = 1
    }

    @TypeConverter
    fun fromInt(value: Int?): NotePackageType? {
        return value?.let {
            when(it) {
                PUBLIC -> NotePackageType.PUBLIC
                ENCRYPT -> NotePackageType.ENCRYPT
                else -> throw IllegalArgumentException("Unknown note package type $it")
            }
        }
    }

    @TypeConverter
    fun toInt(type: NotePackageType?): Int? {
        return type?.let {
            when(it) {
                NotePackageType.PUBLIC -> PUBLIC
                NotePackageType.ENCRYPT -> ENCRYPT
            }
        }
    }
}