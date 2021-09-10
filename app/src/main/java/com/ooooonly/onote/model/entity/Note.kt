package com.ooooonly.onote.model.entity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.io.File
import java.time.Instant
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "note")
data class Note constructor(
    @PrimaryKey(autoGenerate = true)
    override var id: Long? = null,
    var file: File,
    var title: String? = null,
    var brief: String? = null,
    var type: NoteType = NoteType.MARK_DOWN,
    var background: Int? = null,
    @ColumnInfo(name = "package_id")
    var packageId: Long,
    @ColumnInfo(name = "create_time")
    override var createTime: Date = Date.from(Instant.now()),
    @ColumnInfo(name = "modify_time")
    override var modifyTime: Date = Date.from(Instant.now()),
): RoomEntity

enum class NoteType {
    MARK_DOWN
}